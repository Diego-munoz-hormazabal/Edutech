package com.example.ciudad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.ciudad.model.dto.PaisDTO;
import com.example.ciudad.model.entities.Ciudad;
import com.example.ciudad.model.request.ActualizarCiudad;
import com.example.ciudad.model.request.AgregarCiudad;
import com.example.ciudad.repository.CiudadRepository;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository Repository;

    @Autowired
    private WebClient WebClientConfig;

    // LISTAR TODOS LAS CIUDADES
    public List<Ciudad> obtenerTodosLasCiudades() {
        return Repository.findAll();
    }

    // OBTENER CIUDAD POR ID
    public Ciudad obtenerCiudadPorID(int idCiudad) {
        Ciudad ciudad = Repository.findById(idCiudad).orElse(null);
        if (ciudad == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no encontrado.");
        }
        return ciudad;
    }

    // AGREGAR Ciudad
    public Ciudad agregarCiudades(AgregarCiudad nuevaCiudad) {
        PaisDTO paisDTO = null;
        try {
            paisDTO = WebClientConfig.get()
                    .uri("/paises/{idPais}", nuevaCiudad.getIdPais())
                    .retrieve()
                    .bodyToMono(PaisDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad ingresado no encontrado.");
        }
        if (paisDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT no se ha encontrado la ciudad ingresada(IF).");
        }

        Ciudad v = new Ciudad();
        v.setIdPais(nuevaCiudad.getIdPais());
        v.setIdCiudad(nuevaCiudad.getIdCiudad());
        v.setNombreCiudad(nuevaCiudad.getNombreCiudad());

        return Repository.save(v);
    }

    // ACTUALIZAR PROGRESO POR ID
    public Ciudad actualizarCiudad(ActualizarCiudad actualizarCiudad, int idCiudad) {
        // validar
        Ciudad ciudad = Repository.findById(idCiudad).orElse(null);
        if (ciudad == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarno ha encontrado el id ingresado.");
        }
        // validar
        PaisDTO paisDTO = null;
        try {
            paisDTO = WebClientConfig.get()
                    .uri("/paises/{idPais}", actualizarCiudad.getIdPais())
                    .retrieve()
                    .bodyToMono(PaisDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad a actualizar no encontrado.");
        }
        if (paisDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado el dato ingresado(IF).");
        }

        ciudad.setIdPais(actualizarCiudad.getIdPais());
        ciudad.setNombreCiudad(actualizarCiudad.getNombreCiudad());
        return Repository.save(ciudad);

    }

    // ELIMINAR PROGRESO POR ID
    public String eliminarCiudad(int idCiudad) {
        if (Repository.existsById(idCiudad)) {
            Repository.deleteById(idCiudad);
            return "eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT  no ha encontrado la id ingresado(IF).");
        }
    }
}
