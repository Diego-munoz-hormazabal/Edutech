package com.example.Evaluacion.model.entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "evaluacion")
@Data
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvaluacion;

    @Column(name = "nota", nullable=false)
    private Integer nota;

    @Column(name = "fecha_evaluacion", nullable=false)
    private String fechaEvaluacion;

    @Column(name = "id_curso", nullable=false)
    private Integer idCurso;
}
