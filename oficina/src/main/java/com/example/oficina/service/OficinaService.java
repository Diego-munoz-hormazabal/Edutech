package com.example.oficina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.oficina.model.dto.EmpresaDTO;
import com.example.oficina.model.entitie.Oficina;
import com.example.oficina.model.request.ActualizarOficina;
import com.example.oficina.model.request.AgregarOficina;
import com.example.oficina.repository.OficinaRepository;

@Service
public class OficinaService {

    @Autowired
    private OficinaRepository oficinaRepository;

    @Autowired
    private WebClient empresaWebClientConfig;

    // LISTAR TODOS LOS VEHICULOS
    public List<Oficina> obtenerTodosLasOficinas() {
        return oficinaRepository.findAll();
    }

    // OBTENER VEHICULO POR ID
    public Oficina obtenerOficinaPorID(int idOficina) {
        Oficina oficina = oficinaRepository.findById(idOficina).orElse(null);
        if (oficina == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oficina no encontrada.");
        }
        return oficina;
    }

    // AGREGAR VEHICULO
    public Oficina agregarOficina(AgregarOficina nuevaOficina) {
        EmpresaDTO marcadto = null;
        try {
            marcadto = empresaWebClientConfig.get()
                    .uri("/empresas/{id_empresa}", nuevaOficina.getIdEmpresa())
                    .retrieve()
                    .bodyToMono(EmpresaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa de oficina a ingresar no encontrada.");
        }
        if (marcadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT agregarOFicina no ha encontrado la oficina ingresada(IF).");
        }

        Oficina v = new Oficina();
        v.setIdEmpresa(nuevaOficina.getIdEmpresa());
        v.setNombreOficina(nuevaOficina.getNombreOficina());
        v.setTelefonoOficina(nuevaOficina.getTelefonoOficina());
        return oficinaRepository.save(v);
    }

    // ACTUALIZAR VEHICULO POR ID
    public Oficina actualizarOficina(ActualizarOficina actualizarOficina, int idOficina) {
        // validar id_vehiculo
        Oficina vehiculoexistente = oficinaRepository.findById(idOficina).orElse(null);
        if (vehiculoexistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado el id ingresado.");
        }
        // validar id_marca
        EmpresaDTO marcadto = null;
        try {
            marcadto = empresaWebClientConfig.get()
                    .uri("/empresas/{id_empresa}", actualizarOficina.getIdEmpresa())
                    .retrieve()
                    .bodyToMono(EmpresaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A actualizar no encontrada.");
        }
        if (marcadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado los datos ingresados(IF).");
        }
        vehiculoexistente.setTelefonoOficina(actualizarOficina.getTelefonoOficina());
        vehiculoexistente.setIdEmpresa(actualizarOficina.getIdEmpresa());
        vehiculoexistente.setNombreOficina(actualizarOficina.getNombreOficina());

        return oficinaRepository.save(vehiculoexistente);

    }

    // ELIMINAR VEHICULO POR ID
    public String eliminarOficina(int idOficina) {
        if (oficinaRepository.existsById(idOficina)) {
            oficinaRepository.deleteById(idOficina);
            return "eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT eliminar no ha encontrado la id ingresada(IF).");
        }
    }

}
