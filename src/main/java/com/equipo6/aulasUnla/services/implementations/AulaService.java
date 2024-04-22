package com.equipo6.aulasUnla.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo6.aulasUnla.dtos.request.AulaDTORequest;
import com.equipo6.aulasUnla.dtos.response.AulaDTOResponse;
import com.equipo6.aulasUnla.entities.Aula;
import com.equipo6.aulasUnla.entities.Materia;
import com.equipo6.aulasUnla.repositories.AulaRepository;
import com.equipo6.aulasUnla.services.IAulaService;
import com.equipo6.aulasUnla.services.IEdificioService;
import com.equipo6.aulasUnla.services.IMateriaService;

@Service("aulaService")
public class AulaService implements IAulaService{

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    @Autowired
    private IEdificioService edificioServie;

    @Autowired
    private IMateriaService materiaService;

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
    public boolean asignarMateriaAAula(int idAula, String nombreMateria) throws Exception {
        Aula aula = aulaRepository.findById(idAula);
        if(aula == null){
            throw new Exception("Error, el aula con id: "+idAula+" no existe");
        }
        //se trae la materia por el nombre
        Materia materia = materiaService.obtenerMateria(nombreMateria);
        //se asigna la materia al aula
        aula.getMaterias().add(materia);
        
        //dependiendo el turno al que pertenece la materia que se asigna, se ocupa en el aula
        if(materia.getTurno() == "TM"){
            aula.setOcupadoTM(true);
        }
        else if(materia.getTurno() == "TN"){
            aula.setOcupadoTN(true);
        }
        //guardo el aula
        aulaRepository.save(aula);
        
       return true;
    }

    @Override
    public boolean desasignarTodasMaterias() throws Exception {
      for (Aula aula : aulaRepository.findAllAulasAsignadas()) {
        //se vacia el listado de materias de esa aula
        aula.getMaterias().clear();
        aula.setOcupadoTM(false);
        aula.setOcupadoTN(false);
        aulaRepository.save(aula);
      }
      return true;
    }

    @Override
    public boolean desasignarMateriaAAula(int idAula, String nombreMateria) throws Exception {
        Aula aula = aulaRepository.findById(idAula);
        if(aula == null){
            throw new Exception("Error, el aula con id: "+idAula+" no existe");
        }
        //se trae la materia por el nombre
        Materia materia = materiaService.obtenerMateria(nombreMateria);
        //se elimina la materia al aula
        aula.getMaterias().remove(materia);
        
        //dependiendo el turno al que pertenece la materia que se asigna, se desocupa en el aula
        if(materia.getTurno() == "TM"){
            aula.setOcupadoTM(false);
        }
        else if(materia.getTurno() == "TN"){
            aula.setOcupadoTN(false);
        }
        //guardo el aula
        aulaRepository.save(aula);
        
       return true;
    }

}
