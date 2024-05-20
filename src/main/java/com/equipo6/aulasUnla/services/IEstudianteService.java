package com.equipo6.aulasUnla.services;

import com.equipo6.aulasUnla.dtos.request.EstudianteDTORequest;
import com.equipo6.aulasUnla.dtos.response.EstudianteDTOResponse;
import com.equipo6.aulasUnla.entities.Estudiante;

import java.util.List;

public interface IEstudianteService {

    public boolean crearEstudiante(EstudianteDTORequest dto) throws Exception;
    public boolean crearEstudiante(Estudiante estudiante) throws Exception;
    public boolean crearEstudiantes(List<EstudianteDTORequest> dtos) throws Exception;

    public List<EstudianteDTOResponse> obtenerEstudiantes() throws Exception;

    public Estudiante obtenerEstudianteId(int id) throws Exception;

}
