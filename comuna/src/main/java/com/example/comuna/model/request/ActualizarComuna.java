package com.example.comuna.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarComuna {

    @NotBlank(message = "El nombre de la comuna no puede estar vacío")
    @Column(nullable = false)
    private String nombreComuna;

    @NotNull
    private Integer idCiudad;
}
