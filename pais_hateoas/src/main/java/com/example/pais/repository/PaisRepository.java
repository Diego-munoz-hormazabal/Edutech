package com.example.pais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pais.model.entities.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
}
