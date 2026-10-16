package com.example.ProgresoEstudiante.model.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarProgresoEstudiante {

    @NotNull
    private Integer idUsuario;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "100.0", inclusive = true)
    private Double porcentajeAvance;

    @NotBlank
    private String estado;

}
