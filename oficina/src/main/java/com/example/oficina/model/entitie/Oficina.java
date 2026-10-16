package com.example.oficina.model.entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "oficina")
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oficina")
    private Integer idOficina;

    @Column(name = "nombre_oficina", nullable = false)
    private String nombreOficina;

    @Column(name = "telefono_oficina", nullable = false)
    private String telefonoOficina;

    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;
}
