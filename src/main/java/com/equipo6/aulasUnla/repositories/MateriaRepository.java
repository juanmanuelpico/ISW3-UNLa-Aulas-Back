package com.equipo6.aulasUnla.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equipo6.aulasUnla.entities.Materia;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {

    Materia findById(int id);

    @Query(value = "SELECT * FROM materia m WHERE m.nombre = :nombre and m.turno = :turno", nativeQuery = true)
    Materia findMateriaByNombreAndTurno(@Param(value = "nombre") String nombre, @Param(value = "turno") String turno);

    Materia findByNombre(String nombre);

    List<Materia> findAll();

    @Query(value = "SELECT * FROM materia m WHERE m.anio_pertenece = :anio", nativeQuery = true)
    List<Materia> findByAnio(@Param(value = "anio") int anio);

    @Query(value = "SELECT * FROM materia m WHERE m.anio_pertenece = :anio", nativeQuery = true)
    List<Materia> findByNo(@Param(value = "anio") int anio);
    
}
