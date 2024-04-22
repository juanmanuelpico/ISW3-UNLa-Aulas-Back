package com.equipo6.aulasUnla.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo6.aulasUnla.entities.Edificio;
import com.equipo6.aulasUnla.repositories.EdificioRepository;
import com.equipo6.aulasUnla.services.IEdificioServie;

@Service("edificioService")
public class EdificioService implements IEdificioServie {

    @Autowired
    private EdificioRepository edificioRepository;

    @Override
    public Edificio traerEdificioEntidad(int id) throws Exception {
      Edificio edificio = edificioRepository.findById(id);
      if(edificio == null){
        throw new Exception("Error, el edificio no existe");
      }

      return edificio;
    }
    
}
