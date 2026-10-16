package com.example.RegistroRendimiento.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarRegistroRendimiento {

    @NotBlank
    private String tiempoRespuesta;
    @NotBlank
    private String disponibilidadRegistro;
    @NotNull
    private Integer idPlataforma;
}
