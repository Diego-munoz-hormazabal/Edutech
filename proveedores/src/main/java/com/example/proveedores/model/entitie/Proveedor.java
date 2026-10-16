package com.example.proveedores.model.entitie;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "nombre_proveedor", nullable = false)
    private String nombreProveedor;

    @Column(name = "rut_proveedor", nullable = false, unique = true)
    private String rutProveedor;

    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;
}
