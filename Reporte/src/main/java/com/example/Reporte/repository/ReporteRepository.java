package com.example.Reporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Reporte.model.entitie.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
}
