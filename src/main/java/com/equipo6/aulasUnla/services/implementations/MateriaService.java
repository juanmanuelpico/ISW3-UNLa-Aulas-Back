package com.equipo6.aulasUnla.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.equipo6.aulasUnla.dtos.request.MateriaAsignarDocenteDTO;
import com.equipo6.aulasUnla.dtos.response.MateriaDTOResponse;
import com.equipo6.aulasUnla.entities.Docente;
import com.equipo6.aulasUnla.services.IDocenteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.equipo6.aulasUnla.dtos.request.MateriaDTORequest;
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

    @Autowired
    @Lazy
    private IDocenteService docenteService;

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

    @Override
    public List<MateriaDTOResponse> obtenerMaterias() throws Exception{

        List<Materia> materiasObtenidas = materiaRepository.findAll();
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
    public List<MateriaDTOResponse> obtenerMateriasPorAnio(int anio) throws Exception {
        if(anio < 0 || anio > 5){
            throw new Exception("Error, numero de anio invalido, solo se puede del 1 al 5");
        }

        return materiaRepository.findByAnio(anio).stream().map(materia -> modelMapper.map(materia, MateriaDTOResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void actualizarCantEstudiantes(Materia materia) throws Exception {
        materia.setCantEstudiantes(materia.getMateriaEstudianteList().size());
        materiaRepository.save(materia);
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
                mDto.setDocenteACargo(m.getDocente().getNombre()+" "+m.getDocente().getApellido()); // Setear el nombre del docente con apellido
            }else{
                mDto.setDocenteACargo("Sin asignar");
            }
            if(m.getAula() != null) {
                mDto.setAulaAsignada(m.getAula().getNumero()); // Setear el número del aula
                mDto.setTipoDeAula(m.getAula().getTipoDeAula()); // Setear el tipo de aula
            }else{
                mDto.setAulaAsignada(0);
                mDto.setTipoDeAula("Sin asignar");
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
    public Materia obtenerMateria(String nombre, String turno) throws Exception {
        Materia materia = materiaRepository.findMateriaByNombreAndTurno(nombre, turno);

        if(materia == null){
            throw new Exception("Error, la materia con nombre : "+nombre+", no existe");
        }

        return materia;
    }

    @Override
    public Materia obtenerMateria(String nombre) throws Exception {
        Materia materia = materiaRepository.findByNombre(nombre);

        if(materia == null){
            throw new Exception("Error, la materia con nombre : "+nombre+", no existe");
        }

        return materia;
    }

    public boolean asignarDocenteAMateria(MateriaAsignarDocenteDTO dto) throws Exception {
        // Verificar si la materia existe
        Materia materiaEnt = materiaRepository.findById(dto.getIdMateria());

        if (materiaEnt == null) {
            throw new Exception("La materia con id " + dto.getIdMateria() + " no existe");
        }

        Docente docenteEnt = docenteService.traerDocentePorId(dto.getIdDocente());
        if(docenteEnt.getMateria() != null) {
            if(docenteEnt.getMateria().getId() == dto.getIdMateria()){
                throw new Exception("El docente ya se encuentra asignado a la materia que intenta agregarlo");
            }
        }

        // Asignar el docente a la materia
        materiaEnt.setDocente(docenteEnt);
        docenteEnt.setMateria(materiaEnt);
        materiaRepository.save(materiaEnt);

        return true;
    }
    //asignacion de docentes a muchas materias, 1 materia por docente
    public boolean asignarDocentesAMateria(List<MateriaAsignarDocenteDTO> dtos) throws Exception {

        for (MateriaAsignarDocenteDTO  dto : dtos) {
            asignarDocenteAMateria(dto);
        }

        return true;
    }
}