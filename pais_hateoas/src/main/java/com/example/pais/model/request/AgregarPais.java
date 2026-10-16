package com.example.pais.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class AgregarPais {

    @NotBlank
    @Column(nullable = false)
    private String nombrePais;
}
