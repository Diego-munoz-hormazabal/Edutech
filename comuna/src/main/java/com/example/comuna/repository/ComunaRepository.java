package com.example.comuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.comuna.model.entities.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {
}
