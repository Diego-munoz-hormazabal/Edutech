package com.example.Reporte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.Reporte.model.dto.PlataformaEducativaDTO;
import com.example.Reporte.model.entitie.Reporte;
import com.example.Reporte.model.request.ActualizarReporte;
import com.example.Reporte.model.request.AgregarReporte;
import com.example.Reporte.repository.ReporteRepository;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository Repository;

    @Autowired
    private WebClient WebClientConfig;

    // LISTAR TODOS LOS REPORTES
    public List<Reporte> obtenerTodosLosReportes() {
        return Repository.findAll();
    }

    // OBTENER REPORTE POR ID
    public Reporte obtenerReportePorID(int idReporte) {
        Reporte reporte = Repository.findById(idReporte).orElse(null);
        if (reporte == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no encontrado.");
        }
        return reporte;
    }

    // AGREGAR REPORTE
    public Reporte agregarReporte(AgregarReporte nuevaReporte) {
        PlataformaEducativaDTO plataformadto = null;
        try {
            plataformadto = WebClientConfig.get()
                    .uri("/plataformas-educativas/{idPlataforma}", nuevaReporte.getIdPlataforma())
                    .retrieve()
                    .bodyToMono(PlataformaEducativaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plataforma ingresada no encontrada.");
        }
        if (plataformadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT no se ha encontrado la Plataforma ingresada(IF).");
        }

        Reporte v = new Reporte();
        v.setIdPlataforma(nuevaReporte.getIdPlataforma());
        v.setTitulo(nuevaReporte.getTitulo());
        v.setDescripcion(nuevaReporte.getDescripcion());
        v.setTipo(nuevaReporte.getTipo());
        v.setFecha(nuevaReporte.getFecha());
        return Repository.save(v);
    }

    // ACTUALIZAR REPORTE POR ID
    public Reporte actualizarReporte(ActualizarReporte actualizarReporte, int idReporte) {
        // validar 
        Reporte reporteexistente = Repository.findById(idReporte).orElse(null);
        if (reporteexistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado el id ingresado.");
        }
        // validar 
        PlataformaEducativaDTO plataformadto = null;
        try {
            plataformadto = WebClientConfig.get()
                    .uri("/plataformas-educativas/{idPlataforma}", actualizarReporte.getIdPlataforma())
                    .retrieve()
                    .bodyToMono(PlataformaEducativaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dato actualizar no encontrada.");
        }
        if (plataformadto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizar no ha encontrado el dato ingresado(IF).");
        }

        reporteexistente.setIdPlataforma(actualizarReporte.getIdPlataforma());
        reporteexistente.setTitulo(actualizarReporte.getTitulo());
        reporteexistente.setDescripcion(actualizarReporte.getDescripcion());
        reporteexistente.setTipo(actualizarReporte.getTipo());
        reporteexistente.setFecha(actualizarReporte.getFecha());
        return Repository.save(reporteexistente);

    }

    // ELIMINAR REPORTE POR ID
    public String eliminarReporte(int idReporte) {
        if (Repository.existsById(idReporte)) {
            Repository.deleteById(idReporte);
            return "eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT  no ha encontrado la id ingresado(IF).");
        }
    }
}