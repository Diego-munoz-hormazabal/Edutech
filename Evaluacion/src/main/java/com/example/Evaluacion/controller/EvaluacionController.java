package com.example.Evaluacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Evaluacion.model.entitie.Evaluacion;
import com.example.Evaluacion.model.request.ActualizarEvaluacion;
import com.example.Evaluacion.model.request.AgregarEvaluacion;
import com.example.Evaluacion.service.EvaluacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping("")
    public List<Evaluacion> obtenerTodosLasEvaluacions() {
        return evaluacionService.obtenerTodosLasEvaluacion();
    }

    @GetMapping("/{idEvaluacion}")
    public Evaluacion obtenerEvaluacionPorID(@PathVariable int idEvaluacion) {
        return evaluacionService.obtenerEvaluacionPorID(idEvaluacion);
    }

    @PostMapping("")
    public Evaluacion agregarEvaluacion(@Valid @RequestBody AgregarEvaluacion agregarEvaluacion) {
        return evaluacionService.agregarEvaluacion(agregarEvaluacion);
    }

    @PutMapping("/{idEvaluacion}")
    public Evaluacion actualizarEvaluacion(
            @PathVariable int idEvaluacion,
            @Valid @RequestBody ActualizarEvaluacion actualizarEvaluacionDto) {

        return evaluacionService.actualizarEvaluacion(actualizarEvaluacionDto, idEvaluacion);
    }

    @DeleteMapping("/{idEvaluacion}")
    public String eliminarEvaluacion(@PathVariable int idEvaluacion) {
        return evaluacionService.eliminarEvaluacion(idEvaluacion);
    }
}
