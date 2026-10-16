package com.example.Curso.model.entitie;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "curso")
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "nombre_curso", nullable = false)
    private String nombreCurso;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "duracion_horas", nullable = false)
    private Integer duracionHoras;

    @Column(name = "id_contenido", nullable = false)
    private Integer idContenido;
}
