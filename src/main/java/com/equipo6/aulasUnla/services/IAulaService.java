package com.equipo6.aulasUnla.services;

import java.util.List;

import com.equipo6.aulasUnla.dtos.response.AulaDTOResponse;
import com.equipo6.aulasUnla.dtos.response.MateriaDTOResponse;
import com.equipo6.aulasUnla.entities.Materia;

public interface IAulaService {
    
    public List<AulaDTOResponse> obtenerListadoAulas(int cantEstudiantes, String turno, String tipo) throws Exception;

    public MateriaDTOResponse asignarMateriaAAula(int idAula, String nombreMateria, String turno) throws Exception;

    public boolean desasignarTodasMaterias() throws Exception;

    public MateriaDTOResponse desasignarMateriaAAula(int idAula, String nombreMateria, String turno) throws Exception;

}
