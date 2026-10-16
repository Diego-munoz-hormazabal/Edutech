package com.example.Evaluacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.Evaluacion.model.dto.CursoDTO;
import com.example.Evaluacion.model.entitie.Evaluacion;
import com.example.Evaluacion.model.request.ActualizarEvaluacion;
import com.example.Evaluacion.model.request.AgregarEvaluacion;
import com.example.Evaluacion.repository.EvaluacionRepository;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private WebClient cursoWebClientConfig;

    // LISTAR TODAS LAS EVALUACIONES
    public List<Evaluacion> obtenerTodosLasEvaluacion() {
        return evaluacionRepository.findAll();
    }

    // OBTENER EVALUACION POR ID
    public Evaluacion obtenerEvaluacionPorID(int idEvaluacion) {
        Evaluacion evaluacion = evaluacionRepository.findById(idEvaluacion).orElse(null);
        if (evaluacion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no encontrado.");
        }
        return evaluacion;
    }

    // AGREGAR VEHICULO
    public Evaluacion agregarEvaluacion(AgregarEvaluacion nuevaEvaluacion) {
        CursoDTO marcadto = null;
        try {
            marcadto = cursoWebClientConfig.get()
                    .uri("/cursos/{id_curso}", nuevaEvaluacion.getIdCurso())
                    .retrieve()
                    .bodyToMono(CursoDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ingresada no encontrada.");
        }
        if (marcadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT agregar el dato no ha encontrado  ser ingresada(IF).");
        }

        Evaluacion v = new Evaluacion();
        v.setIdCurso(nuevaEvaluacion.getIdCurso());
        v.setNota(nuevaEvaluacion.getNota());
        v.setFechaEvaluacion(nuevaEvaluacion.getFechaEvaluacion());
        
        return evaluacionRepository.save(v);
    }

    // ACTUALIZAR VEHICULO POR ID
    public Evaluacion actualizarEvaluacion(ActualizarEvaluacion actualizarEvaluacion, int idEvaluacion) {
        // validar 
        Evaluacion evaluacionexistente = evaluacionRepository.findById(idEvaluacion).orElse(null);
        if (evaluacionexistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado el id ingresado.");
        }
        // validar 
        CursoDTO marcadto = null;
        try {
            marcadto = cursoWebClientConfig.get()
                    .uri("/cursos/{id_curso}", actualizarEvaluacion.getIdCurso())
                    .retrieve()
                    .bodyToMono(CursoDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se ha pdido actualizar, no encontrada.");
        }
        if (marcadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado la marca ingresada(IF).");
        }

        evaluacionexistente.setIdCurso(actualizarEvaluacion.getIdCurso());
        evaluacionexistente.setNota(actualizarEvaluacion.getNota());
        evaluacionexistente.setFechaEvaluacion(actualizarEvaluacion.getFechaEvaluacion());

        return evaluacionRepository.save(evaluacionexistente);

    }

    // ELIMINAR EVALUACION POR ID
    public String eliminarEvaluacion(int idEvaluacion) {
        if (evaluacionRepository.existsById(idEvaluacion)) {
            evaluacionRepository.deleteById(idEvaluacion);
            return "eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT eliminar no ha encontrado la id ingresada (IF).");
        }
    }
}
