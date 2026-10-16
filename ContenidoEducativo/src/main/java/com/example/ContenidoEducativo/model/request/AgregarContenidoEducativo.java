package com.example.ContenidoEducativo.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarContenidoEducativo {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombreContenido;

    @NotBlank(message = "El tipo de contenido no puede estar vacío")
    private String tipoContenido;

    @NotBlank(message = "La descripción es obligatoria") // O @NotNull
    private String descripcion;

    @NotNull
    private Integer idPlataforma;
}
