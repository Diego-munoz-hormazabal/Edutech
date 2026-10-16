package com.example.PlataformaEducativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PlataformaEducativa.model.entitie.PlataformaEducativa;

public interface PlataformaEducativaRepository
        extends JpaRepository<PlataformaEducativa, Integer> {
}
