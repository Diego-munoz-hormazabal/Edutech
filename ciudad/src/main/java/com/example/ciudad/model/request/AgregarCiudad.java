package com.example.ciudad.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarCiudad {
    private Integer idCiudad;

    @NotBlank
    private String nombreCiudad;

    @NotNull
    private Integer idPais;


}
