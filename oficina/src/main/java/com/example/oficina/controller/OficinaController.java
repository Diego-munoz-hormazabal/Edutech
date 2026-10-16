package com.example.oficina.controller;

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

import com.example.oficina.model.entitie.Oficina;
import com.example.oficina.model.request.ActualizarOficina;
import com.example.oficina.model.request.AgregarOficina;
import com.example.oficina.service.OficinaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/oficinas")
public class OficinaController {

    @Autowired
    private OficinaService oficinaService;

    @GetMapping("")
    public List<Oficina> obtenerTodosLasOficinas() {
        return oficinaService.obtenerTodosLasOficinas();
    }

    @GetMapping("/{idOficina}")
    public Oficina obtenerOficinaPorID(@PathVariable int idOficina) {
        return oficinaService.obtenerOficinaPorID(idOficina);
    }

    @PostMapping("")
    public Oficina agregarOficina(@Valid @RequestBody AgregarOficina agregarOficina) {
        return oficinaService.agregarOficina(agregarOficina);
    }

    @PutMapping("/{idOficina}")
    public Oficina actualizarOficina(
            @PathVariable int idOficina,
            @Valid @RequestBody ActualizarOficina actualizarOficinaDto) {

        return oficinaService.actualizarOficina(actualizarOficinaDto, idOficina);
    }

    @DeleteMapping("/{idOficina}")
    public String eliminarOficina(@PathVariable int idOficina) {
        return oficinaService.eliminarOficina(idOficina);
    }

}
