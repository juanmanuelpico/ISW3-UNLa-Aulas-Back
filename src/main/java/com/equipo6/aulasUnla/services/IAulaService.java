package com.equipo6.aulasUnla.services;

import java.util.List;

import com.equipo6.aulasUnla.dtos.response.AulaDTOResponse;
import com.equipo6.aulasUnla.entities.Materia;

public interface IAulaService {
    
    public List<AulaDTOResponse> obtenerListadoAulas(int cantEstudiantes, String turno) throws Exception;

    public Materia asignarMateriaAAula(int idAula, String nombreMateria) throws Exception;

    public boolean desasignarTodasMaterias() throws Exception;

    public boolean desasignarMateriaAAula(int idAula, String nombreMateria) throws Exception;

}
