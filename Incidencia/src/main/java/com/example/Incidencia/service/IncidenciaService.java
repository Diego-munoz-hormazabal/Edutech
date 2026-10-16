package com.example.Incidencia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.Incidencia.model.dto.PlataformaEducativaDTO;
import com.example.Incidencia.model.entitie.Incidencia;
import com.example.Incidencia.model.request.ActualizarIncidencia;
import com.example.Incidencia.model.request.AgregarIncidencia;
import com.example.Incidencia.repository.IncidenciaRepository;

@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository Repository;

    @Autowired
    private WebClient WebClientConfig;

    // LISTAR TODOS LAS INCIDENCIAS
    public List<Incidencia> obtenerTodosLasIncidencias() {
        return Repository.findAll();
    }

    // OBTENER INCIDENCIA POR ID
    public Incidencia obtenerIncidenciaPorID(int idIncidencia) {
        Incidencia incidencia = Repository.findById(idIncidencia).orElse(null);
        if (incidencia == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no encontrado.");
        }
        return incidencia;
    }

    // AGREGAR INCIDENCIA
    public Incidencia agregarIncidencia(AgregarIncidencia nuevaPlataforma) {
        PlataformaEducativaDTO plataformadto = null;
        try {
            plataformadto = WebClientConfig.get()
                    .uri("/plataformas-educativas/{id_plataforma}", nuevaPlataforma.getIdPlataforma())
                    .retrieve()
                    .bodyToMono(PlataformaEducativaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " id ingresada no encontrada.");
        }
        if (plataformadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT no se ha encontrado la incidencia ingresada(IF).");
        }

        Incidencia v = new Incidencia();
        v.setIdPlataforma(nuevaPlataforma.getIdPlataforma());
        v.setTitulo(nuevaPlataforma.getTitulo());
        v.setDescripcion(nuevaPlataforma.getDescripcion());
        v.setEstado(nuevaPlataforma.getEstado());

        return Repository.save(v);
    }

    // ACTUALIZAR Plataforma POR ID
    public Incidencia actualizarIncidencia(ActualizarIncidencia actualizarIncidencia, int idIncidencia) {
        // validar
        Incidencia incidenciaexistente = Repository.findById(idIncidencia).orElse(null);
        if (incidenciaexistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado el id ingresado.");
        }
        // validar
        PlataformaEducativaDTO plataformadto = null;
        try {
            plataformadto = WebClientConfig.get()
                    .uri("/plataformas-educativas/{id_plataforma}", actualizarIncidencia.getIdPlataforma())
                    .retrieve()
                    .bodyToMono(PlataformaEducativaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dato actualizar no encontrada.");
        }
        if (plataformadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado el dato ingresado(IF).");
        }

        incidenciaexistente.setIdPlataforma(actualizarIncidencia.getIdPlataforma());
        incidenciaexistente.setTitulo(actualizarIncidencia.getTitulo());
        incidenciaexistente.setDescripcion(actualizarIncidencia.getDescripcion());
        incidenciaexistente.setEstado(actualizarIncidencia.getEstado());
        return Repository.save(incidenciaexistente);

    }

    // ELIMINAR INCIDENCIA POR ID
    public String eliminarIncidencia(int idIncidencia) {
        if (Repository.existsById(idIncidencia)) {
            Repository.deleteById(idIncidencia);
            return "eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT  no ha encontrado la id ingresado(IF).");
        }
    }
}
