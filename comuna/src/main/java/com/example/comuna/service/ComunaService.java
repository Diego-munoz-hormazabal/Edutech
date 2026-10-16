package com.example.comuna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.comuna.model.dto.CiudadDTO;
import com.example.comuna.model.entities.Comuna;
import com.example.comuna.model.request.ActualizarComuna;
import com.example.comuna.model.request.AgregarComuna;
import com.example.comuna.repository.ComunaRepository;

@Service
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private WebClient ciudadWebClientConfig;

    // LISTAR TODOS LOS VEHICULOS
    public List<Comuna> obtenerTodosLosComunas() {
        return comunaRepository.findAll();
    }

    // OBTENER VEHICULO POR ID
    public Comuna obtenerComunaPorID(int idComuna) {
        Comuna comuna = comunaRepository.findById(idComuna).orElse(null);
        if (comuna == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado.");
        }
        return comuna;
    }

    // AGREGAR VEHICULO
    public Comuna agregarComuna(AgregarComuna nuevaComuna) {
        CiudadDTO marcadto = null;
        try {
            marcadto = ciudadWebClientConfig.get()
                    .uri("/ciudades/{id_ciudad}", nuevaComuna.getIdCiudad())
                    .retrieve()
                    .bodyToMono(CiudadDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ciudad de comuna a ingresar no encontrada.");
        }
        if (marcadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT agregarComunaVehiculo no ha encontrado la comuna ingresada(IF).");
        }

        Comuna v = new Comuna();
        v.setIdCiudad(nuevaComuna.getIdCiudad());
        v.setNombreComuna(nuevaComuna.getNombreComuna());
        return comunaRepository.save(v);
    }

    // ACTUALIZAR VEHICULO POR ID
    public Comuna actualizarComuna(ActualizarComuna actualizarComuna, int idComuna) {
        // validar id_vehiculo
        Comuna vehiculoexistente = comunaRepository.findById(idComuna).orElse(null);
        if (vehiculoexistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarVehiculo no ha encontrado el id de vehiculo ingresado.");
        }
        // validar id_marca
        CiudadDTO marcadto = null;
        try {
            marcadto = ciudadWebClientConfig.get()
                    .uri("/ciudades/{id_ciudad}", actualizarComuna.getIdCiudad())
                    .retrieve()
                    .bodyToMono(CiudadDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca de vehiculo a actualizar no encontrada.");
        }
        if (marcadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarVehiculo no ha encontrado la marca ingresada(IF).");
        }

        vehiculoexistente.setIdCiudad(actualizarComuna.getIdCiudad());
        vehiculoexistente.setNombreComuna(actualizarComuna.getNombreComuna());

        return comunaRepository.save(vehiculoexistente);

    }

    // ELIMINAR VEHICULO POR ID
    public String eliminarComuna(int idComuna) {
        if (comunaRepository.existsById(idComuna)) {
            comunaRepository.deleteById(idComuna);
            return "Vehiculo eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT eliminarVehiculo no ha encontrado la id_vehiculo ingresado(IF).");
        }
    }
}
