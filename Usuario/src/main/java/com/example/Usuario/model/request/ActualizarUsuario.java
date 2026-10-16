package com.example.Usuario.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarUsuario {

    @NotBlank
    private String nombre;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String rol;
    @NotBlank
    private String estado;
    @NotNull
    private Integer idDireccion;
}
