package com.example.RegistroRendimiento.controller;

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

import com.example.RegistroRendimiento.model.entitie.RegistroRendimiento;
import com.example.RegistroRendimiento.model.request.ActualizarRegistroRendimiento;
import com.example.RegistroRendimiento.model.request.AgregarRegistroRendimiento;
import com.example.RegistroRendimiento.service.RegistroRendimientoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/registro-rendimiento")
public class RegistroRendimientoController {

    @Autowired
    private RegistroRendimientoService registroService;

    @GetMapping("")
    public List<RegistroRendimiento> obtenerTodosLosRegistros() {
        return registroService.obtenerTodosLosRegistroRendimientos();
    }

    @GetMapping("/{idRegistroRendimiento}")
    public RegistroRendimiento obtenerRegistroPorID(@PathVariable int idRegistroRendimiento) {
        return registroService.obtenerRegistroPorID(idRegistroRendimiento);
    }

    @PostMapping("")
    public RegistroRendimiento agregarRegistroRendimiento(
            @Valid @RequestBody AgregarRegistroRendimiento agregarRegistroRendimiento) {
        return registroService.agregarRegistroRendimiento(agregarRegistroRendimiento);
    }

    @PutMapping("/{idRegistroRendimiento}")
    public RegistroRendimiento actualizarRegistroRendimiento(
            @PathVariable int idRegistroRendimiento,
            @Valid @RequestBody ActualizarRegistroRendimiento actualizarPlataformaEducativaDto) {

        return registroService.actualizarRegistroRendimiento(actualizarPlataformaEducativaDto, idRegistroRendimiento);
    }

    @DeleteMapping("/{idRegistroRendimiento}")
    public String eliminarRegistro(@PathVariable int idRegistroRendimiento) {
        return registroService.eliminarRegistro(idRegistroRendimiento);
    }

}
