package com.example.PlataformaEducativa.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarPlataformaEducativa {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombrePlataforma;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String urlPlataforma;
    @NotNull
    private int idEmpresa;
}
