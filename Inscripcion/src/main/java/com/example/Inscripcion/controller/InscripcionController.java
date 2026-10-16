package com.example.Inscripcion.controller;

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

import com.example.Inscripcion.model.entitie.Inscripcion;
import com.example.Inscripcion.model.request.ActualizarInscripcion;
import com.example.Inscripcion.model.request.AgregarInscripcion;
import com.example.Inscripcion.service.InscripcionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping("")
    public List<Inscripcion> obtenerTodosLosInscripciones() {
        return inscripcionService.obtenerTodosLosInscripcion();
    }

    @GetMapping("/{idInscripcion}")
    public Inscripcion obtenerInscripcionPorID(@PathVariable int idInscripcion) {
        return inscripcionService.obtenerInscripcionPorID(idInscripcion);
    }

    @PostMapping("")
    public Inscripcion agregarInscripcion(@Valid @RequestBody AgregarInscripcion agregarInscripcion) {
        return inscripcionService.agregarInscripcion(agregarInscripcion);
    }

    @PutMapping("{idInscripcion}")
    public Inscripcion actualizarInscripcion(@PathVariable int idInscripcion,
            @Valid @RequestBody ActualizarInscripcion actualizarInscripcion) {
        return inscripcionService.actualizarInscripcion(actualizarInscripcion, idInscripcion);
    }

    @DeleteMapping("/{idInscripcion}")
    public String eliminarInscripcion(@PathVariable int idInscripcion) {
        return inscripcionService.eliminarInscripcion(idInscripcion);
    }

}
