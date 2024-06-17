package com.equipo6.aulasUnla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.equipo6.aulasUnla.dtos.request.MateriaAsignarDocenteDTO;
import com.equipo6.aulasUnla.dtos.request.MateriaAsignarUsuariosDTO;
import com.equipo6.aulasUnla.dtos.request.MateriaDTORequest;
import com.equipo6.aulasUnla.dtos.response.MateriaDTOResponse;
import com.equipo6.aulasUnla.dtos.response.MateriaDetalladaDTOResponse;
import com.equipo6.aulasUnla.entities.Materia;
import com.equipo6.aulasUnla.services.IMateriaEstudianteService;
import com.equipo6.aulasUnla.services.IMateriaService;
import com.equipo6.aulasUnla.util.Mensaje;

@RestController
@RequestMapping("/materia")

public class MateriaController {

    @Autowired
    private IMateriaService materiaService;

    @Autowired
    private IMateriaEstudianteService materiaEstudianteService;

    @GetMapping("/")
    public ResponseEntity<Object> obtenerMaterias() {
        try {
            List<MateriaDTOResponse> listaDto = materiaService.obtenerMaterias();
            return ResponseEntity.status(HttpStatus.OK).body(listaDto);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> crearMateria(@RequestBody MateriaDTORequest dto) {
        try {
            materiaService.crearMateria(dto);
            return new ResponseEntity<>(new Mensaje("materia creada con exito"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crearMaterias")
    public ResponseEntity<Object> crearMaterias(@RequestBody List<MateriaDTORequest> dtos) {
        try {
            materiaService.crearMaterias(dtos);
            return new ResponseEntity<>(new Mensaje("materias creada con exito"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /*
     * @PutMapping("/asignarEstudiante/{nombreMateria}/{idEstudiante}")
     * public ResponseEntity<Object> asiganarEstudiante(@PathVariable String
     * nombreMateria,
     * 
     * @PathVariable int idEstudiante) {
     * try {
     * materiaService.agregarEstudiante(nombreMateria, idEstudiante);
     * return new ResponseEntity<>(new Mensaje(
     * "estudiante con id: " + idEstudiante + " agregado correctamente a materia: "
     * + nombreMateria),
     * HttpStatus.OK);
     * } catch (Exception e) {
     * return new ResponseEntity<>(new Mensaje(e.getMessage()),
     * HttpStatus.BAD_REQUEST);
     * }
     * }
     */

    @PutMapping("/asignarEstudiantes")
    public ResponseEntity<Object> asignarEstudiante(@RequestBody MateriaAsignarUsuariosDTO dto) {
        try {
            materiaEstudianteService.asignarEstudiantesAMateria(dto);
            return new ResponseEntity<>(
                    new Mensaje("estudiantes agregados correctamente a materia: " + dto.getNombreMateria()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/asignarDocente")
    public ResponseEntity<Object> asignarDocente(@RequestBody MateriaAsignarDocenteDTO dto) {
        try {
            materiaService.asignarDocenteAMateria(dto);
            return new ResponseEntity<>(
                    new Mensaje("Docente con id: " + dto.getIdDocente() + " agregado exitosamente a materia: "
                            + dto.getIdMateria()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/asignarDocentes")
    public ResponseEntity<Object> asignarDocentes(@RequestBody List<MateriaAsignarDocenteDTO> dtos) {
        try {
            materiaService.asignarDocentesAMateria(dtos);
            return new ResponseEntity<>(
                    new Mensaje("Docentes agregados exitosamente"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("traerMateriasPorAnio/{anio}")
    public ResponseEntity<Object> obtenerMateriasPorAnio(@PathVariable int anio) {
        try {
            List<MateriaDTOResponse> listaDto = materiaService.obtenerMateriasPorAnioConDocenteAulaEdificio(anio);
            return ResponseEntity.status(HttpStatus.OK).body(listaDto);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name/{nombre}")
    public ResponseEntity<Object> obtenerMateriaPorNombre(@PathVariable("nombre") String nombre, @RequestParam String turno) {
        try {
            MateriaDetalladaDTOResponse detalladaDTOResponse = materiaService.tranformarAMateriaDetalladaDTO(materiaService.obtenerMateria(nombre, turno));
            return ResponseEntity.status(HttpStatus.OK).body(detalladaDTOResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}