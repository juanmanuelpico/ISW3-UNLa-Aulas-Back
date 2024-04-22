package com.equipo6.aulasUnla.services;

import com.equipo6.aulasUnla.dtos.request.AulaDTORequest;
import com.equipo6.aulasUnla.dtos.request.EdificioDTORequest;
import com.equipo6.aulasUnla.entities.Edificio;

public interface IEdificioService {

    public boolean crearEdificio(EdificioDTORequest dto) throws Exception;

    public Edificio traerEdificioEntidad(int id) throws Exception;

    public boolean agregarAula(AulaDTORequest dto) throws Exception;
    
}
