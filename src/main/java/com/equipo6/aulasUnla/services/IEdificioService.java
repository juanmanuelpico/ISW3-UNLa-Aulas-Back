package com.equipo6.aulasUnla.services;

import com.equipo6.aulasUnla.dtos.request.AulaDTORequest;
import com.equipo6.aulasUnla.dtos.request.EdificioAgregarAulasDTO;
import com.equipo6.aulasUnla.dtos.request.EdificioDTORequest;
import com.equipo6.aulasUnla.entities.Edificio;

import java.util.List;

public interface IEdificioService {

    public boolean crearEdificio(EdificioDTORequest dto) throws Exception;

    public Edificio traerEdificioEntidad(int id) throws Exception;

    public boolean agregarAula(AulaDTORequest dto) throws Exception;

    public boolean agregarAulas(EdificioAgregarAulasDTO dto) throws Exception;
}
