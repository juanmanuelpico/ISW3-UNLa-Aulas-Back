package com.equipo6.aulasUnla.controller;

import com.equipo6.aulasUnla.dtos.request.EdificioDTORequest;
import com.equipo6.aulasUnla.services.IEdificioService;
import com.equipo6.aulasUnla.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
