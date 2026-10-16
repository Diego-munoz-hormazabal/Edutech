package com.example.Curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.Curso.model.dto.ContenidoDTO;
import com.example.Curso.model.entitie.Curso;
import com.example.Curso.model.request.ActualizarCurso;
import com.example.Curso.model.request.AgregarCurso;
import com.example.Curso.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private WebClient contenidoWebClient; // Cliente para conectar con el servicio de Contenidos

    /*
     * ========================================
     * LISTAR TODOS LOS CURSOS
     * ========================================
     */
    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }

    /*
     * ========================================
     * BUSCAR CURSO POR ID
     * ========================================
     */
    public Curso obtenerCursoPorID(int idCurso) {
        Curso curso = cursoRepository.findById(idCurso).orElse(null);
        if (curso == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado.");
        }
        return curso;
    }

    /*
     * ========================================
     * AGREGAR CURSO (VALIDANDO FK)
     * ========================================
     */
    public Curso agregarCurso(AgregarCurso nuevoCurso) {
        // 1. Validar que el Contenido Educativo exista en el otro microservicio
        ContenidoDTO contenidoDTO = null;
        try {
            contenidoDTO = contenidoWebClient.get()
                    // Asegúrate que esta ruta coincida con tu ContenidoEducativoController
                    // (/contenidos-educativos)
                    .uri("/contenidos-educativos/{id}", nuevoCurso.getIdContenido())
                    .retrieve()
                    .bodyToMono(ContenidoDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "El Contenido Educativo asignado al curso no existe.");
        }

        if (contenidoDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Error al validar el Contenido Educativo (Respuesta nula).");
        }

        // 2. Si existe, procedemos a guardar el curso
        Curso curso = new Curso();
        curso.setNombreCurso(nuevoCurso.getNombreCurso());
        curso.setDescripcion(nuevoCurso.getDescripcion());
        curso.setDuracionHoras(nuevoCurso.getDuracionHoras());
        curso.setIdContenido(nuevoCurso.getIdContenido());

        return cursoRepository.save(curso);
    }

    /*
     * ========================================
     * ACTUALIZAR CURSO
     * ========================================
     */
    public Curso actualizarCurso(ActualizarCurso actualizarCurso, int idCurso) {
        // 1. Verificar si el curso existe
        Curso cursoExistente = cursoRepository.findById(idCurso).orElse(null);
        if (cursoExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el curso a actualizar.");
        }

        // 2. Validar el Contenido Educativo (FK) nuevamente por si cambió el ID
        try {
            contenidoWebClient.get()
                    .uri("/contenidos-educativos/{id}", actualizarCurso.getIdContenido())
                    .retrieve()
                    .bodyToMono(ContenidoDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Contenido Educativo a actualizar no existe.");
        }

        // 3. Actualizar datos
        cursoExistente.setNombreCurso(actualizarCurso.getNombreCurso());
        cursoExistente.setDescripcion(actualizarCurso.getDescripcion());
        cursoExistente.setDuracionHoras(actualizarCurso.getDuracionHoras());
        cursoExistente.setIdContenido(actualizarCurso.getIdContenido());

        return cursoRepository.save(cursoExistente);
    }

    /*
     * ========================================
     * ELIMINAR CURSO
     * ========================================
     */
    public String eliminarCurso(int idCurso) {
        if (cursoRepository.existsById(idCurso)) {
            cursoRepository.deleteById(idCurso);
            return "Curso eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el curso para eliminar.");
        }
    }
}
