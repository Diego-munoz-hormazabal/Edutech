package com.example.PlataformaEducativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.PlataformaEducativa.model.dto.EmpresaDTO;
import com.example.PlataformaEducativa.model.entitie.PlataformaEducativa;
import com.example.PlataformaEducativa.model.request.ActualizarPlataformaEducativa;
import com.example.PlataformaEducativa.model.request.AgregarPlataformaEducativa;
import com.example.PlataformaEducativa.repository.PlataformaEducativaRepository;

@Service
public class PlataformaEducativaService {

    @Autowired
    private PlataformaEducativaRepository plataformaRepository;

    @Autowired
    private WebClient empresaWebClientConfig;

    // LISTAR TODOS Las Plataforma
    public List<PlataformaEducativa> obtenerTodosLasPlataformas() {
        return plataformaRepository.findAll();
    }

    // OBTENER Plataforma POR ID
    public PlataformaEducativa obtenerPlataformaPorID(int idPlataforma) {
        PlataformaEducativa plataformaEducativa = plataformaRepository.findById(idPlataforma).orElse(null);
        if (plataformaEducativa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no encontrado.");
        }
        return plataformaEducativa;
    }

    // AGREGAR Plataforma
    public PlataformaEducativa agregarPlataforma(AgregarPlataformaEducativa nuevaPlataforma) {
        EmpresaDTO marcadto = null;
        try {
            marcadto = empresaWebClientConfig.get()
                    .uri("/empresas/{id_empresa}", nuevaPlataforma.getIdEmpresa())
                    .retrieve()
                    .bodyToMono(EmpresaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ingresada no encontrada.");
        }
        if (marcadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT no se ha encontrado la comuna ingresada(IF).");
        }

        PlataformaEducativa v = new PlataformaEducativa();
        v.setIdEmpresa(nuevaPlataforma.getIdEmpresa());
        v.setNombrePlataforma(nuevaPlataforma.getNombrePlataforma());
        v.setDescripcion(nuevaPlataforma.getDescripcion());
        v.setUrlPlataforma(nuevaPlataforma.getUrlPlataforma());
        return plataformaRepository.save(v);
    }

    // ACTUALIZAR Plataforma POR ID
    public PlataformaEducativa actualizarPlataforma(ActualizarPlataformaEducativa actualizarPlataforma, int idPlataforma) {
        // validar 
        PlataformaEducativa plataformaexistente = plataformaRepository.findById(idPlataforma).orElse(null);
        if (plataformaexistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarno ha encontrado el id ingresado.");
        }
        // validar 
        EmpresaDTO marcadto = null;
        try {
            marcadto = empresaWebClientConfig.get()
                    .uri("/empresas/{id_empresa}", actualizarPlataforma.getIdEmpresa())
                    .retrieve()
                    .bodyToMono(EmpresaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "a actualizar no encontrada.");
        }
        if (marcadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado el dato ingresado(IF).");
        }

        plataformaexistente.setIdEmpresa(actualizarPlataforma.getIdEmpresa());
        plataformaexistente.setNombrePlataforma(actualizarPlataforma.getNombrePlataforma());
        plataformaexistente.setDescripcion(actualizarPlataforma.getDescripcion());
        plataformaexistente.setUrlPlataforma(actualizarPlataforma.getDescripcion());
        return plataformaRepository.save(plataformaexistente);

    }

    // ELIMINAR PLATAFORMA POR ID
    public String eliminarPlataforma(int idPlataforma) {
        if (plataformaRepository.existsById(idPlataforma)) {
            plataformaRepository.deleteById(idPlataforma);
            return "eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT  no ha encontrado la id ingresado(IF).");
        }
    }
}
