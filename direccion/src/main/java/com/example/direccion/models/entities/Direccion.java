package com.example.direccion.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Integer idDireccion;

    @Column(name = "calle", nullable = false)
    private String calle;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "id_comuna", nullable = false)
    private Integer idComuna;
}
