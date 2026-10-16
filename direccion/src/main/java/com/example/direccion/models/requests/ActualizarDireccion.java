package com.example.direccion.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarDireccion {

    @NotBlank
    private String calle;

    @NotBlank
    private String numero;

    @NotNull
    private int idComuna;

}
