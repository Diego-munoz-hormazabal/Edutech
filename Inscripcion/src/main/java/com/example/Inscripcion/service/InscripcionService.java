package com.example.Inscripcion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.Inscripcion.model.dto.PlataformaUsuarioDTO;
import com.example.Inscripcion.model.entitie.Inscripcion;
import com.example.Inscripcion.model.request.ActualizarInscripcion;
import com.example.Inscripcion.model.request.AgregarInscripcion;
import com.example.Inscripcion.repository.InscripcionRepository;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    @Qualifier("usuarioWebClient") // Coincide con el nombre del @Bean en tu config
    private WebClient usuarioWebClient;

    @Autowired
    @Qualifier("plataformaWebClient") // Coincide con el nombre del @Bean en tu config
    private WebClient plataformaWebClient;

    // LISTAR TODOS LOS VEHICULOS
    public List<Inscripcion> obtenerTodosLosInscripcion() {
        return inscripcionRepository.findAll();
    }

    // OBTENER VEHICULO POR ID
    public Inscripcion obtenerInscripcionPorID(int idInscripcion) {
        Inscripcion inscripcion = inscripcionRepository.findById(idInscripcion).orElse(null);
        if (inscripcion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado.");
        }
        return inscripcion;
    }

    // AGREGAR VEHICULO
    public Inscripcion agregarInscripcion(AgregarInscripcion nuevaInscripcion) {
        PlataformaUsuarioDTO plataformaUsuarioDTO = null;
        try {
            plataformaUsuarioDTO = plataformaWebClient.get()
                    .uri("/plataformas-educativas/{idPlataforma}", nuevaInscripcion.getIdPlataforma())
                    .retrieve()
                    .bodyToMono(PlataformaUsuarioDTO.class)
                    .block();
            plataformaUsuarioDTO = usuarioWebClient.get()
                    .uri("/usuarios/{idUsuario}", nuevaInscripcion.getIdUsuario())
                    .retrieve()
                    .bodyToMono(PlataformaUsuarioDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca de vehiculo a ingresar no encontrada.");
        }
        if (plataformaUsuarioDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT agregarInscripcion no ha encontrado la fk ingresada(IF).");
        }

        Inscripcion v = new Inscripcion();
        v.setEstado(nuevaInscripcion.getEstado());
        v.setFechaInscripcion(nuevaInscripcion.getFechaInscripcion());
        v.setIdPlataforma(nuevaInscripcion.getIdPlataforma());
        v.setIdUsuario(nuevaInscripcion.getIdUsuario());
        return inscripcionRepository.save(v);
    }

    // ACTUALIZAR VEHICULO POR ID
    public Inscripcion actualizarInscripcion(ActualizarInscripcion actualizarInscripcion, int idInscripcion) {
        // validar id_vehiculo
        Inscripcion inscripcionExistente = inscripcionRepository.findById(idInscripcion).orElse(null);
        if (inscripcionExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarInscripcion no ha encontrado el id de inscripcion ingresado.");
        }
        // validar id_marca
        PlataformaUsuarioDTO plausaDTO = null;
        try {
            plausaDTO = plataformaWebClient.get()
                    .uri("/plataformas-educativas/{idPlataforma}", actualizarInscripcion.getIdPlataforma())
                    .retrieve()
                    .bodyToMono(PlataformaUsuarioDTO.class)
                    .block();
            plausaDTO = usuarioWebClient.get()
                    .uri("/usuarios/{idUsuario}", actualizarInscripcion.getIdUsuario())
                    .retrieve()
                    .bodyToMono(PlataformaUsuarioDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "fk de inscripcion  a actualizar no encontrada.");
        }
        if (plausaDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarInscripcion no ha encontrado la fk ingresada(IF).");
        }

        inscripcionExistente.setEstado(actualizarInscripcion.getEstado());
        inscripcionExistente.setFechaInscripcion(actualizarInscripcion.getFechaInscripcion());
        inscripcionExistente.setIdPlataforma(actualizarInscripcion.getIdPlataforma());
        inscripcionExistente.setIdUsuario(actualizarInscripcion.getIdUsuario());
        return inscripcionRepository.save(inscripcionExistente);

    }

    // ELIMINAR VEHICULO POR ID
    public String eliminarInscripcion(int idInscripcion) {
        if (inscripcionRepository.existsById(idInscripcion)) {
            inscripcionRepository.deleteById(idInscripcion);
            return "Inscripcion eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT eliminarInscripcion no ha encontrado la id_incripcion ingresado(IF).");
        }
    }
}
