package com.equipo6.aulasUnla.services;

import java.util.List;

import com.equipo6.aulasUnla.dtos.request.MateriaDTORequest;

public interface IMateriaService {
    public boolean crearMateria(MateriaDTORequest dto) throws Exception;

    public boolean crearMaterias(List<MateriaDTORequest> dtos) throws Exception;
}
