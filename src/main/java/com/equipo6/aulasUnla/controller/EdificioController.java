package com.equipo6.aulasUnla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipo6.aulasUnla.dtos.request.AulaDTORequest;
import com.equipo6.aulasUnla.dtos.request.EdificioAgregarAulasDTO;
import com.equipo6.aulasUnla.dtos.request.EdificioDTORequest;
import com.equipo6.aulasUnla.services.IEdificioService;
import com.equipo6.aulasUnla.util.Mensaje;

@RestController
@RequestMapping("/edificio")
@CrossOrigin(origins = "http://localhost:3000") // Permitir solicitudes desde localhost:3000

public class EdificioController {

    @Autowired
    private IEdificioService edificioService;

    @PostMapping("altaEdificio")
    public ResponseEntity<Object> altaEdificio(@RequestBody EdificioDTORequest dto){
        try{
            edificioService.crearEdificio(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Edificio creado exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("agregarAula")
    public ResponseEntity<Object> agregarAula(@RequestBody AulaDTORequest dto){
        try{
            edificioService.agregarAula(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Aula agregada exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("agregarAulas")
    public ResponseEntity<Object> agregarAulas(@RequestBody EdificioAgregarAulasDTO dto){
        try{
            edificioService.agregarAulas(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Aulas agregadas exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

}
