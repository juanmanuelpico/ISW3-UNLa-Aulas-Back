package com.equipo6.aulasUnla.services.implementations;

import java.util.ArrayList;
import java.util.List;

import com.equipo6.aulasUnla.dtos.response.EstudianteDTOResponse;
import com.equipo6.aulasUnla.dtos.response.MateriaDTOResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo6.aulasUnla.dtos.request.MateriaAsignarUsuariosDTO;
import com.equipo6.aulasUnla.dtos.request.MateriaDTORequest;
import com.equipo6.aulasUnla.entities.Estudiante;
import com.equipo6.aulasUnla.entities.Materia;
import com.equipo6.aulasUnla.repositories.MateriaRepository;
import com.equipo6.aulasUnla.services.IEstudianteService;
import com.equipo6.aulasUnla.services.IMateriaService;

@Service("materiaService")
public class MateriaService implements IMateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private IEstudianteService estudianteService;

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
        boolean retorno = false;
        for (MateriaDTORequest dto : dtos) {
//crea todas las materias, excepto las que no cumplen con las condiciones
            try {
                retorno = crearMateria(dto);
            } catch (Exception e) {
              System.out.println(e.getMessage());
            }
        }
        return retorno;
    }

    //se crea este metodo por si tenemos algun problema y debemos asiganar un solo alumno a una sola materia
    @Override
    public boolean agregarEstudiante(String materia, int idEstudiante) throws Exception {
       Materia materiaE = materiaRepository.findByNombre(materia);
       if(materiaE == null){
            throw new Exception("Error, la materia llamada: "+materia+", no existe");
       }

       //se obtiene el estudiante(la excepcion la tirará en su respectivo service)
       Estudiante estudiante = estudianteService.obtenerEstudianteId(idEstudiante);

       //se agrega ese estudiante a la materia
       materiaE.getEstudiantes().add(estudiante);
       materiaRepository.save(materiaE);

        return true;
    }

    //se crea este metodo para ser utilizado en la carga masiva
    @Override
    public boolean agregarEstudiante(Materia materia, int idEstudiante) throws Exception {
        Estudiante estudiante = estudianteService.obtenerEstudianteId(idEstudiante);

        materia.getEstudiantes().add(estudiante);
        materiaRepository.save(materia);
 
         return true;
    }
    
   //carga masiva de estudiantes en una materia
    @Override
    public boolean agregarEstudiantes(MateriaAsignarUsuariosDTO dto) throws Exception {
        //obtenemos la materia a asiganr
        Materia materia = materiaRepository.findByNombre(dto.getNombreMateria());

        if(materia == null){
             throw new Exception("Error, la materia llamada: "+materia+", no existe");
        }
        boolean retorno = false;
        //se agrega cada estudiante en la materia
       for (int id : dto.getIdEstudiantes()) {
          retorno = agregarEstudiante(materia, id);  
       }

       return retorno
       ;
    }

    @Override
    public List<MateriaDTOResponse> obtenerMaterias() throws Exception{

            List<MateriaDTOResponse> listaMateriasDto = new ArrayList<>();
            List<Materia> listaMateriaEnt = materiaRepository.findAll();

            if (listaMateriaEnt.isEmpty()) {
                throw new Exception("La lista de estudiantes esta vacía.");
            }

            for (Materia m : listaMateriaEnt) {
                listaMateriasDto.add(modelMapper.map(m, MateriaDTOResponse.class));
            }

            return listaMateriasDto;

    }


}
