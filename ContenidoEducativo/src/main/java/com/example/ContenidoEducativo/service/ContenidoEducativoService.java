package com.example.ContenidoEducativo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.ContenidoEducativo.model.dto.PlataformaDTO;
import com.example.ContenidoEducativo.model.entitie.ContenidoEducativo;
import com.example.ContenidoEducativo.model.request.ActualizarContenidoEducativo;
import com.example.ContenidoEducativo.model.request.AgregarContenidoEducativo;
import com.example.ContenidoEducativo.repository.ContenidoEducativoRepository;

@Service
public class ContenidoEducativoService {

    @Autowired
    private ContenidoEducativoRepository contenidoRepository;

    @Autowired
    private WebClient PlataformaWebClientConfig;

    // LISTAR TODOS
    public List<ContenidoEducativo> obtenerTodosLosContenidos() {
        return contenidoRepository.findAll();
    }

    // OBTENER POR ID
    public ContenidoEducativo obtenerContenidoPorID(int idContenido) {
        ContenidoEducativo contenido = contenidoRepository.findById(idContenido).orElse(null);
        if (contenido == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenido educativo no encontrado.");
        }
        return contenido;
    }

    // AGREGAR CONTENIDO
    public ContenidoEducativo agregarContenidoEducativo(AgregarContenidoEducativo nuevoContenido) {
        // 1. Validar ID de Plataforma
        PlataformaDTO plataformaDTO = null;
        try {
            plataformaDTO = PlataformaWebClientConfig.get()
                    .uri("/plataformas-educativas/{id_plataforma}", nuevoContenido.getIdPlataforma())
                    .retrieve()
                    .bodyToMono(PlataformaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La plataforma ingresada no existe.");
        }
        if (plataformaDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al validar la plataforma (Respuesta nula).");
        }

        // 2. Crear y guardar
        ContenidoEducativo contenido = new ContenidoEducativo();
        contenido.setIdPlataforma(nuevoContenido.getIdPlataforma());
        contenido.setNombreContenido(nuevoContenido.getNombreContenido());
        contenido.setTipoContenido(nuevoContenido.getTipoContenido());

        // --- AQUÍ ESTÁ EL ARREGLO ---
        contenido.setDescripcion(nuevoContenido.getDescripcion());
        // ---------------------------

        return contenidoRepository.save(contenido);
    }

    // ACTUALIZAR CONTENIDO
    public ContenidoEducativo actualizarContenidoEducativo(ActualizarContenidoEducativo actualizarContenido,
            int idContenido) {

        // 1. Validar que el contenido exista
        ContenidoEducativo contenidoExistente = contenidoRepository.findById(idContenido).orElse(null);
        if (contenidoExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encontró el contenido educativo con el ID ingresado.");
        }

        // 2. Validar que la plataforma exista (FK)
        PlataformaDTO plataformaDTO = null;
        try {
            plataformaDTO = PlataformaWebClientConfig.get()
                    .uri("/plataformas-educativas/{id_plataforma}", actualizarContenido.getIdPlataforma())
                    .retrieve()
                    .bodyToMono(PlataformaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La plataforma a actualizar no existe.");
        }
        if (plataformaDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al validar la plataforma en actualización.");
        }

        // 3. Actualizar datos
        contenidoExistente.setIdPlataforma(actualizarContenido.getIdPlataforma());
        contenidoExistente.setNombreContenido(actualizarContenido.getNombreContenido());
        contenidoExistente.setTipoContenido(actualizarContenido.getTipoContenido());

        // --- AQUÍ ESTÁ EL ARREGLO TAMBIÉN ---
        contenidoExistente.setDescripcion(actualizarContenido.getDescripcion());
        // ------------------------------------

        return contenidoRepository.save(contenidoExistente);
    }

    // ELIMINAR CONTENIDO
    public String eliminarContenido(int idContenido) {
        if (contenidoRepository.existsById(idContenido)) {
            contenidoRepository.deleteById(idContenido);
            return "Contenido educativo eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encontró el contenido para eliminar.");
        }
    }
}
