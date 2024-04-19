package com.equipo6.aulasUnla.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equipo6.aulasUnla.entities.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {


    @Query(value = "SELECT * FROM materia m WHERE m.nombre = :nombre and m.turno = :turno", nativeQuery = true)
    Materia findMateriaByNombreAndTurno(@Param(value = "nombre") String nombre, @Param(value = "turno") String turno);

    Materia findByNombre(String nombre);
    
}
