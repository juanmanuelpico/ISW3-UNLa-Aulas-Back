package com.equipo6.aulasUnla.services.implementations;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo6.aulasUnla.dtos.request.MateriaDTORequest;
import com.equipo6.aulasUnla.entities.Materia;
import com.equipo6.aulasUnla.repositories.MateriaRepository;
import com.equipo6.aulasUnla.services.IMateriaService;

@Service("materiaService")
public class MateriaService implements IMateriaService {

    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired(required = true)
    private ModelMapper modelMapper;
    
    @Override
    public boolean crearMateria(MateriaDTORequest dto) throws Exception {
        
        if(materiaRepository.findMateriaByNombreAndTurno(dto.getNombre(), dto.getTurno()) != null){
            throw new Exception("Error, ya existe la materia en ese turno");
        }

        Materia materia = modelMapper.map(dto, Materia.class);
        materiaRepository.save(materia);
      return true;
    }

    @Override
    public boolean crearMaterias(List<MateriaDTORequest> dtos) throws Exception {

        for (MateriaDTORequest dto : dtos) {
//crea todas las materias, excepto las que no cumplen con las condiciones
            try {
                crearMateria(dto);
            } catch (Exception e) {
              System.out.println(e.getMessage());
            }
        }
        return true;
    }
    
}
