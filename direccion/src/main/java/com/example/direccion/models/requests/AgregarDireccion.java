package com.example.direccion.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class AgregarDireccion {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String calle;

    @NotBlank
    private String numero;

    @NotNull
    private Integer idComuna;
}
