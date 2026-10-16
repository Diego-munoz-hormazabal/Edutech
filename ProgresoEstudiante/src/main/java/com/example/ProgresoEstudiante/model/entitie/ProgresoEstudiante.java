package com.example.ProgresoEstudiante.model.entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "progreso_estudiante")
public class ProgresoEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progreso")
    private Integer idProgreso;

    @Column(name = "porcentaje_avance", nullable = false)
    private Double porcentajeAvance;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

}
