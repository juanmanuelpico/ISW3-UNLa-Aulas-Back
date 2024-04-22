package com.equipo6.aulasUnla.services.implementations;

import com.equipo6.aulasUnla.dtos.request.EdificioDTORequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo6.aulasUnla.entities.Edificio;
import com.equipo6.aulasUnla.repositories.EdificioRepository;
import com.equipo6.aulasUnla.services.IEdificioService;

import java.util.HashSet;

@Service("edificioService")
public class EdificioService implements IEdificioService {

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    @Override
    public boolean crearEdificio(EdificioDTORequest dto) throws Exception {

        if(edificioRepository.findByNombre(dto.getNombre()) != null){
            throw new Exception("Ya existe un edificio con este nombre");
        }

        Edificio edificio = modelMapper.map(dto, Edificio.class);

        edificio.setCantBanios(dto.getCantBanios());
        edificio.setNombre(dto.getNombre());

        edificioRepository.save(edificio);

        return true;
    }

    @Override
    public Edificio traerEdificioEntidad(int id) throws Exception {
      Edificio edificio = edificioRepository.findById(id);
      if(edificio == null){
        throw new Exception("Error, el edificio no existe");
      }

      return edificio;
    }
    
}
