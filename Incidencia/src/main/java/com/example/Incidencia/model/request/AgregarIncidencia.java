package com.example.Incidencia.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarIncidencia {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String titulo;

    @NotBlank
    private String descripcion;

    @NotBlank
    private String estado;

    @NotNull
    private Integer idPlataforma;
}
