package com.equipo6.aulasUnla.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
       //se setea el valor de la cantidad de estudiantes
       materiaE.setCantEstudiantes(materiaE.getEstudiantes().size());
       materiaRepository.save(materiaE);

        return true;
    }

    //se crea este metodo para ser utilizado en la carga masiva
    @Override
    public boolean agregarEstudiante(Materia materia, int idEstudiante) throws Exception {
        Estudiante estudiante = estudianteService.obtenerEstudianteId(idEstudiante);

        materia.getEstudiantes().add(estudiante);
        materia.setCantEstudiantes(materia.getEstudiantes().size()); //asigna el tamaño actual de la lista
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

    @Override
    public List<MateriaDTOResponse> obtenerMateriasPorAnio(int anio) throws Exception {
        if(anio < 0 || anio > 5){
            throw new Exception("Error, numero de anio invalido, solo se puede del 1 al 5");
        }

        return materiaRepository.findByAnio(anio).stream().map(materia -> modelMapper.map(materia, MateriaDTOResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<MateriaDTOResponse> obtenerMateriasPorAnioConDocenteAulaEdificio(int anio) throws Exception {
        if (anio < 0 || anio > 5) {
            throw new Exception("Error, número de año inválido, solo se puede del 1 al 5");
        }
        List<Materia> materiasObtenidas = materiaRepository.findByAnio(anio);
        List<MateriaDTOResponse> materiasResponse = new ArrayList<>();

        for (Materia m : materiasObtenidas) {
            MateriaDTOResponse mDto = modelMapper.map(m, MateriaDTOResponse.class); // Crear el DTO a partir de la materia

            if(m.getDocente() != null) {
                mDto.setDocenteACargo(m.getDocente().getNombre()); // Setear el nombre del docente
            }else{
                mDto.setDocenteACargo("Sin asignar");
            }
            if(m.getAula() != null) {
                mDto.setAulaAsignada(m.getAula().getNumero()); // Setear el número del aula
            }else{
                mDto.setAulaAsignada(0);
            }
            if(m.getAula() != null) {
                if(m.getAula().getEdificio() != null){
                    mDto.setEdificio(m.getAula().getEdificio().getNombre()); // Setear el nombre del edificio
                }
            }else {
                mDto.setEdificio("Sin asignar");
            }
            materiasResponse.add(mDto); // Agregar el DTO modificado a la lista de respuesta
        }
        return materiasResponse;
    }


    @Override
    public Materia obtenerMateria(String nombre) throws Exception {
       Materia materia = materiaRepository.findByNombre(nombre);

       if(materia == null){
        throw new Exception("Error, la materia con nombre : "+nombre+", no existe");
       }

       return materia;
    }
    
   

  


}
