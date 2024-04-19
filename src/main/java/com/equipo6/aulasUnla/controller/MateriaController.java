package com.equipo6.aulasUnla.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
