package com.equipo6.aulasUnla.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo6.aulasUnla.dtos.request.AulaDTORequest;
import com.equipo6.aulasUnla.dtos.response.AulaDTOResponse;
import com.equipo6.aulasUnla.entities.Aula;
import com.equipo6.aulasUnla.repositories.AulaRepository;
import com.equipo6.aulasUnla.services.IAulaService;
import com.equipo6.aulasUnla.services.IEdificioService;

@Service("aulaService")
public class AulaService implements IAulaService{

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    @Autowired
    private IEdificioService edificioServie;

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

    @Override
    public boolean agregarAula(AulaDTORequest dto) throws Exception {
        Aula aula = modelMapper.map(dto, Aula.class);
        //si el edificio no existe se corta la ejecución al tirar la excepcion del lado de EdificioService
        aula.setEdificio(edificioServie.traerEdificioEntidad(dto.getIdEdificio()));
        //arranca estando disponible en ambos turnos
        aula.setOcupadoTM(false);
        aula.setOcupadoTN(false);
        aulaRepository.save(aula);
        return true;
        
    }
    
}
