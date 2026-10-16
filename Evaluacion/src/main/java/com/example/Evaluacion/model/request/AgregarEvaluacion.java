package com.example.Evaluacion.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarEvaluacion {

    @NotNull
    private Integer nota;

    @NotBlank(message = "La fecha no puede estar vacío")
    private String  fechaEvaluacion;

    @NotNull
    private Integer idCurso;
}
