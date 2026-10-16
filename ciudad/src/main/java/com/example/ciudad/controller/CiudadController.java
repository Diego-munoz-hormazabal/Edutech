package com.example.ciudad.controller;

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

import com.example.ciudad.model.entities.Ciudad;
import com.example.ciudad.model.request.ActualizarCiudad;
import com.example.ciudad.model.request.AgregarCiudad;
import com.example.ciudad.service.CiudadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadService Service;

    @GetMapping("")
    public List<Ciudad> obtenerTodosLasCiudades() {
        return Service.obtenerTodosLasCiudades();
    }

    @GetMapping("/{idCiudad}")
    public Ciudad obtenerCiudadPorID(@PathVariable int idCiudad) {
        return Service.obtenerCiudadPorID(idCiudad);
    }

    @PostMapping("")
    public Ciudad agregarCiudad(@Valid @RequestBody AgregarCiudad agregarCiudad) {
        return Service.agregarCiudades(agregarCiudad);
    }

    @PutMapping("{idCiudad}")
    public Ciudad actualizarCiudad(
            @PathVariable int idCiudad,
            @Valid @RequestBody ActualizarCiudad actualizarDto) {

        return Service.actualizarCiudad(actualizarDto, idCiudad);
    }

    @DeleteMapping("/{idCiudad}")
    public String eliminarCiudad(@PathVariable int idCiudad) {
        return Service.eliminarCiudad(idCiudad);
    }

}
