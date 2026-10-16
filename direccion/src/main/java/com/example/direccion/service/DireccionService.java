package com.example.direccion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.direccion.models.dto.DireccionDTO;
import com.example.direccion.models.entities.Direccion;
import com.example.direccion.models.requests.ActualizarDireccion;
import com.example.direccion.models.requests.AgregarDireccion;
import com.example.direccion.repository.DireccionRepository;

@Service
public class DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private WebClient comunaWebClienConfig;

    // LISTAR TODOS LOS VEHICULOS
    public List<Direccion> obtenerTodasLasDirecciones() {
        return direccionRepository.findAll();
    }

    // OBTENER VEHICULO POR ID
    public Direccion obtenerDireccionPorID(int idVehiculo) {
        Direccion vehiculo = direccionRepository.findById(idVehiculo).orElse(null);
        if (vehiculo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado.");
        }
        return vehiculo;
    }

    // AGREGAR VEHICULO
    public Direccion agregarDireccion(AgregarDireccion nuevVehiculo) {
        DireccionDTO marcadto = null;
        try {
            marcadto = comunaWebClienConfig.get()
                    .uri("/comunas/{id_comuna}", nuevVehiculo.getIdComuna())
                    .retrieve()
                    .bodyToMono(DireccionDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "comuna de direccion a ingresar no encontrada.");
        }
        if (marcadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT agregarDireccion no ha encontrado la comuna ingresada(IF).");
        }

        Direccion v = new Direccion();
        v.setCalle(nuevVehiculo.getCalle());
        v.setIdComuna(nuevVehiculo.getIdComuna());
        v.setNumero(nuevVehiculo.getNumero());
        return direccionRepository.save(v);
    }

    // ACTUALIZAR VEHICULO POR ID
    public Direccion actualizarDireccion(ActualizarDireccion actualizarDireccion, int id_vehiculo) {
        // validar id_vehiculo
        Direccion vehiculoexistente = direccionRepository.findById(id_vehiculo).orElse(null);
        if (vehiculoexistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarVehiculo no ha encontrado el id de vehiculo ingresado.");
        }
        // validar id_marca
        DireccionDTO marcadto = null;
        try {
            marcadto = comunaWebClienConfig.get()
                    .uri("/comunas/{id_comuna}", actualizarDireccion.getIdComuna())
                    .retrieve()
                    .bodyToMono(DireccionDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca de vehiculo a actualizar no encontrada.");
        }
        if (marcadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarVehiculo no ha encontrado la marca ingresada(IF).");
        }

        vehiculoexistente.setCalle(actualizarDireccion.getCalle());
        vehiculoexistente.setIdComuna(actualizarDireccion.getIdComuna());
        vehiculoexistente.setNumero(actualizarDireccion.getNumero());
        return direccionRepository.save(vehiculoexistente);

    }

    // ELIMINAR VEHICULO POR ID
    public String eliminarDireccion(int id_vehiculo) {
        if (direccionRepository.existsById(id_vehiculo)) {
            direccionRepository.deleteById(id_vehiculo);
            return "direccion eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT eliminarDireccion no ha encontrado la id_direccion ingresado(IF).");
        }
    }

}
