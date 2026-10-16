package com.example.PlataformaEducativa.model.entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity

@Data
@Table(name = "plataforma_educativa")
public class PlataformaEducativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plataforma", nullable = false)
    private Integer idPlataforma;

    @Column(name = "nombrePlataforma", nullable = false)
    private String nombrePlataforma;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "urlPlataforma", nullable = false)
    private String urlPlataforma;

    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;

}
