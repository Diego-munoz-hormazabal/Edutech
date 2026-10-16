package com.example.ProgresoEstudiante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.ProgresoEstudiante.model.dto.UsuarioDTO;
import com.example.ProgresoEstudiante.model.entitie.ProgresoEstudiante;
import com.example.ProgresoEstudiante.model.request.ActualizarProgresoEstudiante;
import com.example.ProgresoEstudiante.model.request.AgregarProgresoEstudiante;
import com.example.ProgresoEstudiante.repository.ProgresoEstudianteRepository;

@Service
public class ProgresoEstudianteService {

    @Autowired
    private ProgresoEstudianteRepository Repository;

    @Autowired
    private WebClient WebClientConfig;

    // LISTAR TODOS LOS PROGRESOS ESTUDIANTE
    public List<ProgresoEstudiante> obtenerTodosLpsProgresoEstudiantes() {
        return Repository.findAll();
    }

    // OBTENER PROGRESO ESTUDIANTE POR ID
    public ProgresoEstudiante obtenerPlataformaPorID(int idProgreso) {
        ProgresoEstudiante progresoEstudiante = Repository.findById(idProgreso).orElse(null);
        if (progresoEstudiante == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no encontrado.");
        }
        return progresoEstudiante;
    }

    // AGREGAR Plataforma
    public ProgresoEstudiante agregarProgresoEstudiante(AgregarProgresoEstudiante nuevaPlataforma) {
        UsuarioDTO usuarioDTO = null;
        try {
            usuarioDTO = WebClientConfig.get()
                    .uri("/usuarios/{idUsuario}", nuevaPlataforma.getIdUsuario())
                    .retrieve()
                    .bodyToMono(UsuarioDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Progreso ingresado no encontrado.");
        }
        if (usuarioDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT no se ha encontrado el progreso ingresada(IF).");
        }

        ProgresoEstudiante v = new ProgresoEstudiante();
        v.setIdUsuario(nuevaPlataforma.getIdUsuario());
        v.setPorcentajeAvance(nuevaPlataforma.getPorcentajeAvance());
        v.setEstado(nuevaPlataforma.getEstado());

        return Repository.save(v);
    }

    // ACTUALIZAR PROGRESO POR ID
    public ProgresoEstudiante actualizarProgresoEstudiante(ActualizarProgresoEstudiante actualizarProgresoEstudiante,
            int idProgreso) {
        // validar
        ProgresoEstudiante progresoEstudiante = Repository.findById(idProgreso).orElse(null);
        if (progresoEstudiante == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarno ha encontrado el id ingresado.");
        }
        // validar
        UsuarioDTO usuarioDTO = null;
        try {
            usuarioDTO = WebClientConfig.get()
                    .uri("/usuarios/{idUsuario}", actualizarProgresoEstudiante.getIdUsuario())
                    .retrieve()
                    .bodyToMono(UsuarioDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Progreso a actualizar no encontrado.");
        }
        if (usuarioDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado el dato ingresado(IF).");
        }

        progresoEstudiante.setIdUsuario(actualizarProgresoEstudiante.getIdUsuario());
        progresoEstudiante.setPorcentajeAvance(actualizarProgresoEstudiante.getPorcentajeAvance());
        progresoEstudiante.setEstado(actualizarProgresoEstudiante.getEstado());

        return Repository.save(progresoEstudiante);

    }

    // ELIMINAR PROGRESO POR ID
    public String eliminarProgreso(int idProgreso) {
        if (Repository.existsById(idProgreso)) {
            Repository.deleteById(idProgreso);
            return "eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT  no ha encontrado la id ingresado(IF).");
        }
    }
}
