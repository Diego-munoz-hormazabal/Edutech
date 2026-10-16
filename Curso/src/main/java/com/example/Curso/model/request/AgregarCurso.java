package com.example.Curso.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarCurso {
    

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombreCurso;

    @NotBlank(message = "La descripción no puede estar vacía")  
    private String descripcion;

    @NotNull(message = "La duración en horas no puede estar vacía")
    private Integer duracionHoras;

    @NotNull
    private Integer idContenido;
}
