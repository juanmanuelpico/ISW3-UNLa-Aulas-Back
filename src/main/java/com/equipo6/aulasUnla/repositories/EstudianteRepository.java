package com.equipo6.aulasUnla.repositories;

import com.equipo6.aulasUnla.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    Estudiante findById(int id);

    Estudiante findByDni(String dni);

    Estudiante findByEmail(String email);

    List<Estudiante> findAll();


}
