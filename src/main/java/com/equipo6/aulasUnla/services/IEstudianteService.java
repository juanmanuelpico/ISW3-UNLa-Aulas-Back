package com.equipo6.aulasUnla.services;

import com.equipo6.aulasUnla.dtos.request.EstudianteDTORequest;

import java.util.List;

public interface IEstudianteService {

    public boolean crearEstudiante(EstudianteDTORequest dto) throws Exception;

    public boolean crearEstudiantes(List<EstudianteDTORequest> dtos) throws Exception;
}
