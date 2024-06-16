package com.equipo6.aulasUnla.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equipo6.aulasUnla.entities.Aula;

public interface AulaRepository extends JpaRepository<Aula, Integer>{

    Aula findById(int id);

    //trae todas las aulas que sean aptas para la cantidad de estudiantes y que estan libres en el TURNO MAÑANA
    /*@Query(value = "SELECT * FROM aula a WHERE a.capacidad >= :cant AND a.ocupado_TM = false AND a.tipo_de_aula = :tipo", nativeQuery = true)
    List<Aula> findAulasForMateriaTM(@Param("cant")int cantEstudiantes, @Param("tipo") String tipo);

     //trae todas las aulas que sean aptas para la cantidad de estudiantes y que estan libres en el TURNO NOCHE
    @Query(value = "SELECT * FROM aula a WHERE a.capacidad >= :cant AND a.ocupado_TN = false AND a.tipo_de_aula = :tipo", nativeQuery = true)
    List<Aula> findAulasForMateriaTN(@Param("cant")int cantEstudiantes, @Param("tipo") String tipo);
    */

    //QUERY DEFINITIVA TENIENDO EN CUENTA QUE UN AULA PUEDE SER OCUPADA TODOS LOS DIAS DE LA SEMANA EN AMBOS TUROS
        @Query(value = "SELECT a.* FROM aula a " +
            "WHERE ocupado_tm = 0" +
            " AND a.capacidad >= :cant AND a.tipo_de_aula = :tipo", nativeQuery = true)
    List<Aula> findAulasForMateriaTM(@Param("cant")int cantEstudiantes, @Param("tipo") String tipo);
    @Query(value = "SELECT a.* FROM aula a " +
            "WHERE ocupado_tn = 0" +
            " AND a.capacidad >= :cant AND a.tipo_de_aula = :tipo", nativeQuery = true)
    List<Aula> findAulasForMateriaTN(@Param("cant")int cantEstudiantes, @Param("tipo") String tipo);



    //trae todas las aulas que sean aptas para la cantidad de estudiantes y que estan libres en el TURNO NOCHE
    @Query(value = "SELECT * FROM aula a WHERE a.capacidad >= :cant", nativeQuery = true)
    List<Aula> findAllAulasForMateria(@Param("cant")int cantEstudiantes);

    @Query(value = "SELECT * FROM aula a WHERE a.ocupado_TM = true OR a.ocupado_TN = true", nativeQuery = true)
    List<Aula> findAllAulasAsignadas();


    
}
