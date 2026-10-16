package com.example.comuna.controller;

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

import com.example.comuna.model.entities.Comuna;
import com.example.comuna.model.request.ActualizarComuna;
import com.example.comuna.model.request.AgregarComuna;
import com.example.comuna.service.ComunaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping("")
    public List<Comuna> obtenerTodosLosComunas() {
        return comunaService.obtenerTodosLosComunas();
    }

    @GetMapping("/{idComuna}")
    public Comuna obtenerComunaPorID(@PathVariable int idComuna) {
        return comunaService.obtenerComunaPorID(idComuna);
    }

    @PostMapping("")
    public Comuna agregarComuna(@Valid @RequestBody AgregarComuna agregarComuna) {
        return comunaService.agregarComuna(agregarComuna);
    }

    @PutMapping("/{idComuna}")
    public Comuna actualizarComuna(
            @PathVariable int idComuna,
            @Valid @RequestBody ActualizarComuna actualizarComunaDto) {

        return comunaService.actualizarComuna(actualizarComunaDto, idComuna);
    }

    @DeleteMapping("/{idComuna}")
    public String eliminarComuna(@PathVariable int idComuna) {
        return comunaService.eliminarComuna(idComuna);
    }
}
