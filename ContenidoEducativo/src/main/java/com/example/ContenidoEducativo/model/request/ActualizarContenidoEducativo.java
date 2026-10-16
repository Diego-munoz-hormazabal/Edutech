package com.example.ContenidoEducativo.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarContenidoEducativo {

    @NotBlank
    private String nombreContenido;

    @NotBlank
    private String tipoContenido;

    @NotBlank(message = "La descripción es obligatoria") // O @NotNull
    private String descripcion;

    @NotNull
    private Integer idPlataforma;

}
