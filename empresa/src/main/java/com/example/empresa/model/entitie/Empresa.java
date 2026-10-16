package com.example.empresa.model.entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    
    @Column(name = "nombre_empresa", nullable = false)
    private String nombreEmpresa;

    @Column(name = "rut_empresa", nullable = false, unique = true)
    private String rutEmpresa;

    @Column(name = "id_direccion", nullable = false)
    private Integer idDireccion;
}
