package com.equipo6.aulasUnla.controller;

import com.equipo6.aulasUnla.dtos.request.AulaDTORequest;
import com.equipo6.aulasUnla.dtos.response.AulaDTOResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/agregarMateria/{idAula}/{nombreMateria}")
    public ResponseEntity<Object> obtenerListadoAulasDisponiblesEnTurno(@PathVariable int idAula, @PathVariable String nombreMateria){
        try {
            boolean materiaAsignada = aulaService.asignarMateriaAAula(idAula, nombreMateria);
            return new ResponseEntity<>(new Mensaje("Materia: "+nombreMateria+", asignada a aula con id: "+idAula+" exitosamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/desasignarMateriasDeAulas")
    public ResponseEntity<Object> desasiganarMateriasDeAulas(){
        try {
            aulaService.desasignarTodasMaterias();
            return new ResponseEntity<>(new Mensaje("Limpieza exitosa. Las aulas ya no tienen ninguna materia"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

  

    
    
}
