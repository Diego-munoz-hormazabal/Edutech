package com.example.pais.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pais")
@Data

public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPais;

    @Column(nullable = false)
    private String nombrePais;
}
