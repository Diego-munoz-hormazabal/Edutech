package com.example.ciudad.model.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarCiudad {

    private Integer idCiudad;

    @NotBlank(message = "El nombre de la ciudad no puede estar vacío ni con espacios")
    private String nombreCiudad;

    @NotNull
    private Integer idPais;
}
