package com.example.Curso.controller;

/*
 * ========================================
 * IMPORTS BÁSICOS
 * ========================================
 */
import java.util.List;

/*
 * IMPORTS SPRING WEB
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * IMPORTS MODELOS Y SERVICIO
 */
import com.example.Curso.model.entitie.Curso;
import com.example.Curso.model.request.ActualizarCurso;
import com.example.Curso.model.request.AgregarCurso;
import com.example.Curso.service.CursoService;

import jakarta.validation.Valid;

/*
 * ========================================
 * CONTROLADOR REST CURSOS
 * ========================================
 */
@RestController
@RequestMapping("cursos") // Define la ruta base: http://localhost:PORT/cursos
public class CursoController {

    @Autowired
    private CursoService cursoService;

    /*
     * ========================================
     * GET - LISTAR TODOS
     * URL: GET /cursos
     * ========================================
     */
    @GetMapping("")
    public List<Curso> obtenerTodosLosCursos() {
        return cursoService.obtenerTodosLosCursos();
    }

    /*
     * ========================================
     * GET - BUSCAR POR ID
     * URL: GET /cursos/1
     * ========================================
     */
    @GetMapping("/{idCurso}")
    public Curso obtenerCursoPorId(@PathVariable int idCurso) {
        return cursoService.obtenerCursoPorID(idCurso);
    }

    /*
     * ========================================
     * POST - CREAR CURSO
     * URL: POST /cursos
     * ========================================
     */
    @PostMapping("")
    public Curso agregarCurso(@Valid @RequestBody AgregarCurso nuevoCurso) {
        return cursoService.agregarCurso(nuevoCurso);
    }

    /*
     * ========================================
     * PUT - ACTUALIZAR CURSO
     * URL: PUT /cursos/1
     * NOTA: Requiere ID en la URL y Datos en el Body
     * ========================================
     */
    @PutMapping("/{idCurso}")
    public Curso actualizarCurso(
            @PathVariable int idCurso,
            @Valid @RequestBody ActualizarCurso actualizar) {

        // Enviamos ID y DTO al servicio (siguiendo la lógica de CursoService)
        return cursoService.actualizarCurso(actualizar, idCurso);
    }

    /*
     * ========================================
     * DELETE - ELIMINAR CURSO
     * URL: DELETE /cursos/1
     * ========================================
     */
    @DeleteMapping("/{idCurso}")
    public String eliminarCurso(@PathVariable int idCurso) {
        return cursoService.eliminarCurso(idCurso);
    }
}
