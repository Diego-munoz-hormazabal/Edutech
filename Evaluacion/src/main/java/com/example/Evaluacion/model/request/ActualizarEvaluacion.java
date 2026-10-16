package com.example.Evaluacion.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarEvaluacion {

    @NotNull
    private Integer nota;

    @NotBlank
    private String fechaEvaluacion;

    @NotNull
    private Integer idCurso;
}
