package com.example.Incidencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Incidencia.model.entitie.Incidencia;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Integer> {
}
