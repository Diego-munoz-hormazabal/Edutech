package com.example.proveedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.proveedores.model.entitie.Proveedor;
import com.example.proveedores.model.request.ActualizarProveedores;
import com.example.proveedores.model.request.AgregarProveedores;
import com.example.proveedores.service.ProveedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/proveedor")
public class ProveedoresController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("")
    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorService.obtenerTodosLosProveedores();
    }

    @GetMapping("/{id_proveedor}")
    public Proveedor obtenerProveedorPorId(@PathVariable Integer id_proveedor) {
        return proveedorService.obtenerProveedorPorID(id_proveedor);
    }

    @PostMapping("")
    public Proveedor agregarProveedor(@Valid @RequestBody AgregarProveedores agregarProveedores) {
        return proveedorService.agregarProveedor(agregarProveedores);
    }

    @PutMapping("/{id_proveedor}")
    public Proveedor actualizarProveedor(
            @PathVariable Integer id_proveedor,
            @Valid @RequestBody ActualizarProveedores actualizarProveedores) {
        return proveedorService.actualizarProveedor(id_proveedor, actualizarProveedores);
    }

    @DeleteMapping("/{id_proveedor}")
    public String eliminarProveedor(@PathVariable Integer id_proveedor) {
        return proveedorService.eliminarProveedor(id_proveedor);
    }
}
