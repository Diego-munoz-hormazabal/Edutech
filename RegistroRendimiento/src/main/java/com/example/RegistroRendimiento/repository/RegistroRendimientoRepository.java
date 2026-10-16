package com.example.RegistroRendimiento.repository;

import com.example.RegistroRendimiento.model.entitie.RegistroRendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRendimientoRepository
        extends JpaRepository<RegistroRendimiento, Integer> {
}
