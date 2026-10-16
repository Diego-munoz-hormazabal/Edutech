package com.example.ContenidoEducativo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ContenidoEducativo.model.entitie.ContenidoEducativo;

public interface ContenidoEducativoRepository
        extends JpaRepository<ContenidoEducativo, Integer> {
}
