package com.example.oficina.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarOficina {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombreOficina;
    @NotBlank
    private String telefonoOficina;
    @NotNull
    private Integer idEmpresa;
}
