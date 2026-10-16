package com.example.Curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Curso.model.entitie.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
