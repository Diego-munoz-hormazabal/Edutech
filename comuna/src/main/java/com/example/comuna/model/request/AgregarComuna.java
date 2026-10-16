package com.example.comuna.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarComuna {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombreComuna;

    @NotNull
    private Integer idCiudad;

}
