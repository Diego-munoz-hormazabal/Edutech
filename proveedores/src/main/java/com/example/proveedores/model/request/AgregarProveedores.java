package com.example.proveedores.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AgregarProveedores {
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombreProveedor;

    @Column(nullable=false)
    private String rutProveedor;

    @Column(nullable=false)
    private Integer idEmpresa;

}
