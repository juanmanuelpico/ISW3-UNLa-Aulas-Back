package com.equipo6.aulasUnla.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equipo6.aulasUnla.entities.Aula;

public interface AulaRepository extends JpaRepository<Aula, Integer>{

    //trae todas las aulas que sean aptas para la cantidad de estudiantes y que estan libres en el TURNO MAÑANA
    @Query(value = "SELECT * FROM aula a WHERE a.capacidad >= :cant AND a.ocupado_TM = false", nativeQuery = true)
    List<Aula> findAulasForMateriaTM(@Param("cant")int cantEstudiantes);

     //trae todas las aulas que sean aptas para la cantidad de estudiantes y que estan libres en el TURNO NOCHE
    @Query(value = "SELECT * FROM aula a WHERE a.capacidad >= :cant AND a.ocupado_TN = false", nativeQuery = true)
    List<Aula> findAulasForMateriaTN(@Param("cant")int cantEstudiantes);

    //trae todas las aulas que sean aptas para la cantidad de estudiantes y que estan libres en el TURNO NOCHE
    @Query(value = "SELECT * FROM aula a WHERE a.capacidad >= :cant", nativeQuery = true)
    List<Aula> findAllAulasForMateria(@Param("cant")int cantEstudiantes);
    
}
