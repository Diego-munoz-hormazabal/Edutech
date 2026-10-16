package com.example.direccion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.direccion.models.entities.Direccion;
import com.example.direccion.models.requests.ActualizarDireccion;
import com.example.direccion.models.requests.AgregarDireccion;
import com.example.direccion.service.DireccionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/direccion")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    // LISTAR
    @GetMapping
    public List<Direccion> listar() {
        return direccionService.obtenerTodasLasDirecciones();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Direccion buscarPorId(@PathVariable int id) {
        return direccionService.obtenerDireccionPorID(id);
    }

    // GUARDAR
    @PostMapping
    public Direccion guardar(
            @Valid @RequestBody AgregarDireccion agregarDireccion) {
        return direccionService.agregarDireccion(agregarDireccion);
    }

    // ACTUALIZAR (ID VA EN LA URL)
    @PutMapping("/{id}")
    public Direccion actualizar(
            @PathVariable int id,
            @Valid @RequestBody ActualizarDireccion actualizarDireccion) {
        return direccionService.actualizarDireccion(actualizarDireccion, id);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        return direccionService.eliminarDireccion(id);
    }
}
