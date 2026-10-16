package com.example.comuna.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "comuna")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comuna")
    private Integer idComuna;

    @Column(name = "nombre_comuna", nullable = false)
    private String nombreComuna;

    @Column(name = "id_ciudad", nullable = false)
    private Integer idCiudad; // relación lógica (NO FK)
}
