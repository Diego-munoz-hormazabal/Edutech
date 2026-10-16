package com.example.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oficina.model.entitie.Oficina;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Integer> {
}
