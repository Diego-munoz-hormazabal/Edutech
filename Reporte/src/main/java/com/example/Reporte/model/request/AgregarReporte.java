package com.example.Reporte.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarReporte {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String titulo;

    @NotBlank
    private String descripcion;

    @NotBlank
    private String tipo;

    @NotBlank
    private String fecha;

    @NotNull
    private Integer idPlataforma;
}
