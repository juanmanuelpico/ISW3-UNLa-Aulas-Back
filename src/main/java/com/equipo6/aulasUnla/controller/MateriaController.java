package com.equipo6.aulasUnla.controller;


import java.util.List;

import com.equipo6.aulasUnla.dtos.response.MateriaDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.equipo6.aulasUnla.dtos.request.MateriaAsignarUsuariosDTO;
import com.equipo6.aulasUnla.dtos.request.MateriaDTORequest;
import com.equipo6.aulasUnla.services.IMateriaService;
import com.equipo6.aulasUnla.util.Mensaje;

@RestController
@RequestMapping("/materia")
@CrossOrigin(origins = "http://localhost:3000")
public class MateriaController {

    @Autowired
    private IMateriaService materiaService;

    @PostMapping("/crear")
    public ResponseEntity<Object> crearMateria(@RequestBody MateriaDTORequest dto){
        try {
            materiaService.crearMateria(dto);
            return new ResponseEntity<>(new Mensaje("materia creada con exito"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crearMaterias")
    public ResponseEntity<Object> crearMaterias(@RequestBody List<MateriaDTORequest> dtos){
        try {
            materiaService.crearMaterias(dtos);
            return new ResponseEntity<>(new Mensaje("materias creada con exito"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/asignarEstudiante/{nombreMateria}/{idEstudiante}")
    public ResponseEntity<Object> asiganarEstudiante(@PathVariable String nombreMateria, @PathVariable int idEstudiante){
        try {
            materiaService.agregarEstudiante(nombreMateria, idEstudiante);
            return new ResponseEntity<>(new Mensaje("estudiante con id: "+ idEstudiante+ " agregado correctamente a materia: "+ nombreMateria), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/asignarEstudiantes")
    public ResponseEntity<Object> asiganarEstudiante(@RequestBody MateriaAsignarUsuariosDTO dto){
        try {
            materiaService.agregarEstudiantes(dto);
            return new ResponseEntity<>(new Mensaje("estudiantes agregados correctamente a materia: "+ dto.getNombreMateria()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("traerMaterias")
    public ResponseEntity<Object> obtenerMaterias(){
        try{
            List<MateriaDTOResponse> listaDto = materiaService.obtenerMaterias();
            return ResponseEntity.status(HttpStatus.OK).body(listaDto);
        } catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("traerMateriasPorAnio/{anio}")
    public ResponseEntity<Object> obtenerMateriasPorAnio(@PathVariable int anio){
        try{
            List<MateriaDTOResponse> listaDto = materiaService.obtenerMateriasPorAnio(anio);
            return ResponseEntity.status(HttpStatus.OK).body(listaDto);
        } catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }
    
}
