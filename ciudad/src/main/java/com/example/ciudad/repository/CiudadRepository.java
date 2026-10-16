package com.example.ciudad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ciudad.model.entities.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
}
