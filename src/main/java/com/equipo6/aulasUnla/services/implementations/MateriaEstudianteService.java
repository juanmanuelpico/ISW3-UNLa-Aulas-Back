package com.equipo6.aulasUnla.services.implementations;

import com.equipo6.aulasUnla.dtos.request.MateriaAsignarUsuariosDTO;
import com.equipo6.aulasUnla.entities.Estudiante;
import com.equipo6.aulasUnla.entities.Materia;
import com.equipo6.aulasUnla.entities.MateriaEstudiante;
import com.equipo6.aulasUnla.repositories.MateriaEstudianteRepository;
import com.equipo6.aulasUnla.services.IEstudianteService;
import com.equipo6.aulasUnla.services.IMateriaEstudianteService;
import com.equipo6.aulasUnla.services.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("materiaEstudianteService")
public class MateriaEstudianteService implements IMateriaEstudianteService {

    @Autowired
    private MateriaEstudianteRepository materiaEstudianteRepository;
    @Autowired
    private IMateriaService materiaService;

    @Autowired
    private IEstudianteService estudianteService;
    @Override
    public void asignarEstudianteAMateria(String nombreMateria, int idEstudiante) throws Exception {
        Materia materia = materiaService.obtenerMateria(nombreMateria);
        Estudiante estudiante = estudianteService.obtenerEstudianteId(idEstudiante);
        MateriaEstudiante materiaEstudiante = new MateriaEstudiante();
        materiaEstudiante.setMateria(materia);
        materiaEstudiante.setEstudiante(estudiante);
        materiaEstudiante.setFechaInscripcion(LocalDate.now());
        materiaEstudianteRepository.save(materiaEstudiante);
        materiaService.actualizarCantEstudiantes(materia);
    }

    @Override
    public void asignarEstudiantesAMateria(MateriaAsignarUsuariosDTO dto) throws Exception {
        Materia materia = materiaService.obtenerMateria(dto.getNombreMateria(), dto.getTurno());

        for(int idEstudiante : dto.getIdEstudiantes()){
            Estudiante estudiante = estudianteService.obtenerEstudianteId(idEstudiante);
            if(materiaEstudianteRepository.findByMateriaAndEstudiante(materia.getId(), estudiante.getId()) != null){
                throw new Exception("Error, ya existe el estudiante con id: "+ estudiante.getId()+" ya està inscripto en la materia: "+ materia.getNombre());
            }
            MateriaEstudiante materiaEstudiante = new MateriaEstudiante();
            materiaEstudiante.setMateria(materia);
            materiaEstudiante.setEstudiante(estudiante);
            materiaEstudiante.setFechaInscripcion(LocalDate.now());
            materiaEstudianteRepository.save(materiaEstudiante);
        }
        //se actualiza la cantidad de estudiantes
        materiaService.actualizarCantEstudiantes(materia);
    }
}
