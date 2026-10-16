package com.example.Inscripcion.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarInscripcion {

    @NotNull
    private Integer idPlataforma;
    @NotBlank
    private String fechaInscripcion;
    @NotBlank
    private String estado;
    @NotNull
    private Integer idUsuario;
}
