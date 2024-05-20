package com.equipo6.aulasUnla.services;

import com.equipo6.aulasUnla.dtos.request.DocenteDTORequest;
import com.equipo6.aulasUnla.dtos.response.DocenteDTOResponse;
import com.equipo6.aulasUnla.entities.Docente;

import java.util.List;

public interface IDocenteService {

    public boolean crearDocente(DocenteDTORequest dto) throws Exception;

    public boolean crearDocentes(List<DocenteDTORequest> dtos) throws Exception;

    public Docente traerDocentePorId(int id) throws Exception;

    public List<DocenteDTOResponse> obtenerDocentes()throws Exception;

}
