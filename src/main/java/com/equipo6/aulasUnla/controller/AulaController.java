package com.equipo6.aulasUnla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipo6.aulasUnla.dtos.response.AulaDTOResponse;
import com.equipo6.aulasUnla.entities.Materia;
import com.equipo6.aulasUnla.services.IAulaService;
import com.equipo6.aulasUnla.util.Mensaje;

@RestController
@RequestMapping("/aula")
@CrossOrigin(origins = "http://localhost:3000")

public class AulaController {

    @Autowired
    private IAulaService aulaService;

    @GetMapping("/traer/{turno}/{cantidadEstudiantes}")
    public ResponseEntity<Object> obtenerListadoAulasDisponiblesEnTurno(@PathVariable String turno, @PathVariable int cantidadEstudiantes){
        try {
            List<AulaDTOResponse> aulasDto = aulaService.obtenerListadoAulas(cantidadEstudiantes, turno);
            return new ResponseEntity<>(aulasDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{idAula}/asignarMateriaAula/{nombreMateria}")
    public ResponseEntity<Object> asignarMateriaAula(@PathVariable int idAula, @PathVariable String nombreMateria){
        try {
            Materia materiaAsignada = aulaService.asignarMateriaAAula(idAula, nombreMateria);
            return new ResponseEntity<>(materiaAsignada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    //desasigna todas las materias de las aulas
    @DeleteMapping("/desasignarMateriasDeAulas")
    public ResponseEntity<Object> desasiganarMateriasDeAulas(){
        try {
            aulaService.desasignarTodasMaterias();
            return new ResponseEntity<>(new Mensaje("Limpieza exitosa. Las aulas ya no tienen ninguna materia asignada"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    //desasigna unicamente la materia del aula recibido por parametro
    @DeleteMapping("/desasignarMateria/{idAula}/{nombreMateria}")
    public ResponseEntity<Object> desasiganarMateria(@PathVariable int idAula, @PathVariable String nombreMateria ){
        try {
            aulaService.desasignarMateriaAAula(idAula, nombreMateria);
            return new ResponseEntity<>(new Mensaje("Desasignación exitosa"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}