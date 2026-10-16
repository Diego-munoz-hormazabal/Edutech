package com.example.PlataformaEducativa.controller;

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

import com.example.PlataformaEducativa.model.entitie.PlataformaEducativa;
import com.example.PlataformaEducativa.model.request.ActualizarPlataformaEducativa;
import com.example.PlataformaEducativa.model.request.AgregarPlataformaEducativa;
import com.example.PlataformaEducativa.service.PlataformaEducativaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/plataformas-educativas")
public class PlataformaEducativaController {

    @Autowired
    private PlataformaEducativaService plataformaService;

    @GetMapping("")
    public List<PlataformaEducativa> obtenerTodosLasPlataformas() {
        return plataformaService.obtenerTodosLasPlataformas();
    }

    @GetMapping("/{idPlataforma}")
    public PlataformaEducativa obtenerPlataformaPorID(@PathVariable int idPlataforma) {
        return plataformaService.obtenerPlataformaPorID(idPlataforma);
    }

    @PostMapping("")
    public PlataformaEducativa agregarPlataforma(
            @Valid @RequestBody AgregarPlataformaEducativa agregarPlataformaEducativa) {
        return plataformaService.agregarPlataforma(agregarPlataformaEducativa);
    }

    @PutMapping("/{idPlataforma}")
    public PlataformaEducativa actualizarPlataforma(
            @PathVariable int idPlataforma,
            @Valid @RequestBody ActualizarPlataformaEducativa actualizarEmpresaDto) {

        return plataformaService.actualizarPlataforma(actualizarEmpresaDto, idPlataforma);
    }

    @DeleteMapping("/{idPlataforma}")
    public String eliminarPlataforma(@PathVariable int idPlataforma) {
        return plataformaService.eliminarPlataforma(idPlataforma);
    }

}
