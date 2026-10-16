package com.example.proveedores.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarProveedores {

    @NotBlank
    private String nombreProveedor;

    @NotBlank
    private String rutProveedor;

    @NotNull
    private Integer idEmpresa;
}
