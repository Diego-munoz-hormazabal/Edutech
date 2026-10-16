package com.example.oficina.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarOficina {

    @NotBlank
    private String nombreOficina;

    @NotBlank
    private String telefonoOficina;

    @NotNull
    private int idEmpresa;

}
