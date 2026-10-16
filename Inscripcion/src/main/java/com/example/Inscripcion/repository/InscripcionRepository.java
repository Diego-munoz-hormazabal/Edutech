package com.example.Inscripcion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Inscripcion.model.entitie.Inscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
}
