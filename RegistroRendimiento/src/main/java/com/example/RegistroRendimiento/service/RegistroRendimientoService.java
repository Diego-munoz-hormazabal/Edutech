package com.example.RegistroRendimiento.service;

import com.example.RegistroRendimiento.model.dto.PlataformaEducativaDTO;
import com.example.RegistroRendimiento.model.entitie.RegistroRendimiento;
import com.example.RegistroRendimiento.model.request.AgregarRegistroRendimiento;
import com.example.RegistroRendimiento.model.request.ActualizarRegistroRendimiento;
import com.example.RegistroRendimiento.repository.RegistroRendimientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class RegistroRendimientoService {

    @Autowired
    private RegistroRendimientoRepository registroRepository;

    @Autowired
    private WebClient plataformaEducativaWebClientConfig;

    // LISTAR TODOS LOS REGISTROS
    public List<RegistroRendimiento> obtenerTodosLosRegistroRendimientos() {
        return registroRepository.findAll();
    }

    // OBTENER REGISTRO POR ID
    public RegistroRendimiento obtenerRegistroPorID(int idRegistroRendimiento) {
        RegistroRendimiento registroRendimiento = registroRepository.findById(idRegistroRendimiento).orElse(null);
        if (registroRendimiento == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no encontrado.");
        }
        return registroRendimiento;
    }

    // AGREGAR REGISTRO RENDIMIENTO
    public RegistroRendimiento agregarRegistroRendimiento(AgregarRegistroRendimiento nuevaPlataforma) {
        PlataformaEducativaDTO plataformaEducativadto = null;
        try {
            plataformaEducativadto = plataformaEducativaWebClientConfig.get()
                    .uri("/plataformas-educativas/{idPlataforma}", nuevaPlataforma.getIdPlataforma())
                    .retrieve()
                    .bodyToMono(PlataformaEducativaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ingresada no encontrada.");
        }
        if (plataformaEducativadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT no se ha encontrado el registro ingresada(IF).");
        }

        RegistroRendimiento v = new RegistroRendimiento();
        v.setIdPlataforma(nuevaPlataforma.getIdPlataforma());
        v.setTiempoRespuesta(nuevaPlataforma.getTiempoRespuesta());
        v.setDisponibilidadRegistro(nuevaPlataforma.getDisponibilidadRegistro());
       
        return registroRepository.save(v);
    }

    // ACTUALIZAR REGISTRO POR ID
    public RegistroRendimiento actualizarRegistroRendimiento(ActualizarRegistroRendimiento actualizarRegistroRendimiento, int idRegistroRendimiento) {
        // validar 
        RegistroRendimiento registroexistente = registroRepository.findById(idRegistroRendimiento).orElse(null);
        if (registroexistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarno ha encontrado el id ingresado.");
        }
        // validar 
        PlataformaEducativaDTO marcadto = null;
        try {
            marcadto = plataformaEducativaWebClientConfig.get()
                    .uri("/plataformas-educativas/{idPlataforma}", actualizarRegistroRendimiento.getIdPlataforma())
                    .retrieve()
                    .bodyToMono(PlataformaEducativaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "a actualizar no encontrada.");
        }
        if (marcadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado el dato ingresado(IF).");
        }

        registroexistente.setIdPlataforma(actualizarRegistroRendimiento.getIdPlataforma());
        registroexistente.setDisponibilidadRegistro(actualizarRegistroRendimiento.getDisponibilidadRegistro());
        registroexistente.setTiempoRespuesta(actualizarRegistroRendimiento.getTiempoRespuesta());
        return registroRepository.save(registroexistente);

    }

    // ELIMINAR REGISTRO POR ID
    public String eliminarRegistro(int idRegistroRendimiento) {
        if (registroRepository.existsById(idRegistroRendimiento)) {
            registroRepository.deleteById(idRegistroRendimiento);
            return "eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT  no ha encontrado la id ingresado(IF).");
        }
    }
}
