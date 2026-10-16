package com.example.Curso.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarCurso {

    @NotBlank
    private String nombreCurso;

    @NotBlank
    private String descripcion;

    @NotNull
    private Integer duracionHoras;

    @NotNull
    private Integer idContenido;
}
