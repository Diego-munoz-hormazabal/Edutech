package com.example.RegistroRendimiento.model.entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "registro_rendimiento")
public class RegistroRendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_rendimiento")
    private Integer idRegistroRendimiento;

    @Column(name = "tiempo_respuesta", nullable = false)
    private String tiempoRespuesta;

    @Column(name = "disponibilidad_registro", nullable = false)
    private String disponibilidadRegistro;
    
    @Column(name = "id_plataforma", nullable = false)
    private Integer idPlataforma;
}
