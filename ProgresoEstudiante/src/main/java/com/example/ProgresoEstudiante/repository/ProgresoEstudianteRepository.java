package com.example.ProgresoEstudiante.repository;

import com.example.ProgresoEstudiante.model.entitie.ProgresoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgresoEstudianteRepository
        extends JpaRepository<ProgresoEstudiante, Integer> {
}
