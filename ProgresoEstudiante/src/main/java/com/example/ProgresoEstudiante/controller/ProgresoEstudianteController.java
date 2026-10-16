package com.example.ProgresoEstudiante.controller;

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

import com.example.ProgresoEstudiante.model.entitie.ProgresoEstudiante;
import com.example.ProgresoEstudiante.model.request.ActualizarProgresoEstudiante;
import com.example.ProgresoEstudiante.model.request.AgregarProgresoEstudiante;
import com.example.ProgresoEstudiante.service.ProgresoEstudianteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/progreso-estudiantes")
public class ProgresoEstudianteController {

    @Autowired
    private ProgresoEstudianteService Service;

    @GetMapping("")
    public List<ProgresoEstudiante> obtenerTodosLosProgresoEstudiantes() {
        return Service.obtenerTodosLpsProgresoEstudiantes();
    }

    @GetMapping("/{idProgreso-estudiante}")
    public ProgresoEstudiante obtenerProgresoPorID(@PathVariable int idProgreso) {
        return Service.obtenerPlataformaPorID(idProgreso);
    }

    @PostMapping("")
    public ProgresoEstudiante agregarProgresoEstudiante(@Valid @RequestBody AgregarProgresoEstudiante agregarProgresoEstudiante) {
        return Service.agregarProgresoEstudiante(agregarProgresoEstudiante);
    }

    @PutMapping("{idProgreso}")
    public ProgresoEstudiante actualizarProgresoEstudiante(
            @PathVariable int idProgreso,
            @Valid @RequestBody ActualizarProgresoEstudiante actualizarDto) {

        return Service.actualizarProgresoEstudiante(actualizarDto, idProgreso);
    }

    @DeleteMapping("/{idProgreso}")
    public String eliminarProgreso(@PathVariable int idProgreso) {
        return Service.eliminarProgreso(idProgreso);
    }
    
}
