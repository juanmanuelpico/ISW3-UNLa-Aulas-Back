package com.equipo6.aulasUnla.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo6.aulasUnla.dtos.response.AulaDTOResponse;
import com.equipo6.aulasUnla.entities.Aula;
import com.equipo6.aulasUnla.repositories.AulaRepository;
import com.equipo6.aulasUnla.services.IAulaService;

@Service("aulaService")
public class AulaService implements IAulaService{

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    @Override
    public List<AulaDTOResponse> obtenerListadoAulas(int cantEstudiantes, String turno) throws Exception {
       if(turno.isBlank() || turno.isEmpty() || (turno != "TN" && turno !="TM")){
        throw new Exception("Error, el turno es invalido");
       }
       List<Aula> aulas = null;

       if(turno == "TM"){
        aulas = aulaRepository.findAulasForMateriaTM(cantEstudiantes);
       }
       else if(turno == "TN"){
        aulas = aulaRepository.findAulasForMateriaTN(cantEstudiantes);
       }
       
       return aulas.stream().map(aula -> modelMapper.map(aula, AulaDTOResponse.class)).collect(Collectors.toList());
    }
    
}
