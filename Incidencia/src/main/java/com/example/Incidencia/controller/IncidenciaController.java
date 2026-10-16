package com.example.Incidencia.controller;

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

import com.example.Incidencia.model.entitie.Incidencia;
import com.example.Incidencia.model.request.ActualizarIncidencia;
import com.example.Incidencia.model.request.AgregarIncidencia;
import com.example.Incidencia.service.IncidenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {

    @Autowired
    private IncidenciaService Service;

    @Autowired
    @GetMapping("")
    public List<Incidencia> obtenerTodosLasIncidencias() {
        return Service.obtenerTodosLasIncidencias();
    }

    @GetMapping("/{idIncidencia}")
    public Incidencia obtenerIncidenciaPorID(@PathVariable int idIncidencia) {
        return Service.obtenerIncidenciaPorID(idIncidencia);
    }

    @PostMapping("")
    public Incidencia agregarIncidencia(@Valid @RequestBody AgregarIncidencia agregarIncidencia) {
        return Service.agregarIncidencia(agregarIncidencia);
    }

    @PutMapping("/{idIncidencia}")
    public Incidencia actualizarIncidencia(
            @PathVariable int idIncidencia,
            @Valid @RequestBody ActualizarIncidencia actualizarIncidenciaDto) {

        return Service.actualizarIncidencia(actualizarIncidenciaDto, idIncidencia);
    }

    @DeleteMapping("/{idIncidencia}")
    public String eliminarIncidencia(@PathVariable int idIncidencia) {
        return Service.eliminarIncidencia(idIncidencia);
    }

}
