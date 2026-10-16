package com.example.ContenidoEducativo.model.entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "contenido_educativo")
@Data
public class ContenidoEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContenido;

    @Column(name = "nombre_contenido", nullable = false)
    private String nombreContenido;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipo_contenido", nullable = false)
    private String tipoContenido;

    @Column(name = "id_plataforma", nullable = false)
    private Integer idPlataforma;
}
