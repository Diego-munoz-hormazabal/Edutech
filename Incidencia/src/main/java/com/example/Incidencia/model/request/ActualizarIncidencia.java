package com.example.Incidencia.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarIncidencia {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String estado;
    @NotNull
    private Integer idPlataforma;
}
