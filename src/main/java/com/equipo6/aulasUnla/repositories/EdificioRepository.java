package com.equipo6.aulasUnla.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equipo6.aulasUnla.entities.Edificio;

public interface EdificioRepository extends JpaRepository<Edificio, Integer> {
    Edificio findByNombre(String nombre);
    Edificio findById(int id);
}
