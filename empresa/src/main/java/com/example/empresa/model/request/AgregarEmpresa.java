package com.example.empresa.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarEmpresa {

    @NotNull
    private Integer idEmpresa;
    @NotBlank
    private String nombreEmpresa;
    @NotBlank
    private String rutEmpresa;
    @NotNull
    private Integer idDireccion;
}
