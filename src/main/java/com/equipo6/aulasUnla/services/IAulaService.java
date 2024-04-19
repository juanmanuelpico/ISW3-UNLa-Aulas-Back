package com.equipo6.aulasUnla.services;

import java.util.List;

import com.equipo6.aulasUnla.dtos.response.AulaDTOResponse;

public interface IAulaService {
    
    public List<AulaDTOResponse> obtenerListadoAulas(int cantEstudiantes, String turno) throws Exception;
}
