package com.example.Reporte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reporte.model.entitie.Reporte;
import com.example.Reporte.model.request.ActualizarReporte;
import com.example.Reporte.model.request.AgregarReporte;
import com.example.Reporte.service.ReporteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService; // Cambiado nombre de variable a minúscula

    @GetMapping("")
    public List<Reporte> obtenerTodosLosReportes() {
        return reporteService.obtenerTodosLosReportes();
    }

    // CORREGIDO: idReporte (ruta) == idReporte (parámetro)
    @GetMapping("/{idReporte}")
    public Reporte obtenerReportePorID(@PathVariable int idReporte) {
        return reporteService.obtenerReportePorID(idReporte);
    }

    @PostMapping("")
    public Reporte agregarReporte(@Valid @RequestBody AgregarReporte agregarReporte) {
        return reporteService.agregarReporte(agregarReporte);
    }

    @PutMapping("/{idReporte}")
    public Reporte actualizarReporte(
            @PathVariable int idReporte,
            @Valid @RequestBody ActualizarReporte actualizarDto) {
        return reporteService.actualizarReporte(actualizarDto, idReporte);
    }

    // CORREGIDO: idReporte (ruta) == idReporte (parámetro)
    @DeleteMapping("/{idReporte}")
    public String eliminarReporte(@PathVariable int idReporte) {
        return reporteService.eliminarReporte(idReporte);
    }
}
