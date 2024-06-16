package com.equipo6.aulasUnla.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.equipo6.aulasUnla.dtos.response.MateriaDTOResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

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
    public List<AulaDTOResponse> obtenerListadoAulas(int cantEstudiantes, String turno, String tipo) throws Exception {
       if(turno.isBlank() || turno.isEmpty() || (!turno.equals("TN") && !turno.equals("TM"))){
        throw new Exception("Error, el turno es invalido");
       }
       List<Aula> aulas = null;

        //dependiendo el turno se ejecuta una query
        if(turno.equals("TM")){
            aulas = aulaRepository.findAulasForMateriaTM(cantEstudiantes, tipo);
        } else if (turno.equals("TN")) {
            aulas = aulaRepository.findAulasForMateriaTN(cantEstudiantes, tipo);
        }


        List<AulaDTOResponse> dtos = new ArrayList<>();

        for(Aula aula : aulas){
            AulaDTOResponse dto = modelMapper.map(aula, AulaDTOResponse.class);
            dto.setEdificio(aula.getEdificio().getNombre());
            dtos.add(dto);
        }
       return dtos;
    }

    //Devuelve un response de materia, para actualizar el listado de materias con la materia editada en el front
    @Override
    public MateriaDTOResponse asignarMateriaAAula(int idAula, String nombreMateria, String turno) throws Exception {
        Aula aula = aulaRepository.findById(idAula);
        if(aula == null){
            throw new Exception("Error, el aula con id: "+idAula+" no existe");
        }
        //se trae la materia por el nombre y turno
        Materia materia = materiaService.obtenerMateria(nombreMateria, turno);
        //se asigna la materia al aula

        aula.getMaterias().add(materia);
        materia.setAula(aula);
        
        //dependiendo el turno al que pertenece la materia que se asigna, se ocupa en el aula
        if(materia.getTurno().equals("TM")){
            aula.setOcupadoTM(true);
        }
        else if(materia.getTurno().equals("TN")){
            aula.setOcupadoTN(true);
        }
        //guardo el aula
        aulaRepository.save(aula);
        
       return materiaService.tranformarADto(materia);
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

    //Devuelve un response de materia, para actualizar el listado de materias con la materia editada en el front
    @Override
    public MateriaDTOResponse desasignarMateriaAAula(int idAula, String nombreMateria, String turno) throws Exception {
        Aula aula = aulaRepository.findById(idAula);
        if(aula == null){
            throw new Exception("Error, el aula con id: "+idAula+" no existe");
        }
        //se trae la materia por el nombre y turno
        Materia materia = materiaService.obtenerMateria(nombreMateria, turno);
        //se elimina la materia al aula
        aula.getMaterias().remove(materia);
        materia.setAula(null);
        
        //dependiendo el turno al que pertenece la materia que se asigna, se desocupa en el aula
        if(materia.getTurno().equals("TM")){
            aula.setOcupadoTM(false);
        }
        else if(materia.getTurno().equals("TN")){
            aula.setOcupadoTN(false);
        }
        //guardo el aula
        aulaRepository.save(aula);

        return materiaService.tranformarADto(materia);
    }

}
