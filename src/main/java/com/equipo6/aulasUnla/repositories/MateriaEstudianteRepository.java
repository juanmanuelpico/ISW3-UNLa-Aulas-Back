package com.equipo6.aulasUnla.repositories;

import com.equipo6.aulasUnla.entities.MateriaEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MateriaEstudianteRepository extends JpaRepository<MateriaEstudiante, Integer> {

    @Query(value = "SELECT * FROM materia_estudiante me WHERE me.id_materia = :idMateria AND me.id_estudiante = :idEstudiante", nativeQuery = true)
    MateriaEstudiante findByMateriaAndEstudiante(@Param("idMateria") int idMateria, @Param("idEstudiante") int idEstudiante);
}
