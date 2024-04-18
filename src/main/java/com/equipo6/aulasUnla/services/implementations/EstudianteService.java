package com.equipo6.aulasUnla.services.implementations;

import com.equipo6.aulasUnla.dtos.request.EstudianteDTORequest;
import com.equipo6.aulasUnla.entities.Estudiante;
import com.equipo6.aulasUnla.repositories.EstudianteRepository;
import com.equipo6.aulasUnla.services.IEstudianteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("estudianteService")
public class EstudianteService implements IEstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    @Override
    public boolean crearEstudiante(EstudianteDTORequest dto) throws Exception {

        if(estudianteRepository.findByDni(dto.getDni()) != null){
            throw new Exception("El dni para este estudiante ya existe");
        }
        if(estudianteRepository.findByEmail(dto.getEmail()) != null) {
            throw new Exception("El mail ya se encuentra registrado, cambie de mail");
        }

        Estudiante estudiante = modelMapper.map(dto, Estudiante.class);
        estudianteRepository.save(estudiante);
        return true;
    }

    @Override
    public boolean crearEstudiantes(List<EstudianteDTORequest> dtos) throws Exception {
        return false;
    }
}
