package com.equipo6.aulasUnla.controller;

import com.equipo6.aulasUnla.dtos.request.DocenteDTORequest;
import com.equipo6.aulasUnla.dtos.response.DocenteDTOResponse;
import com.equipo6.aulasUnla.services.IDocenteService;
import com.equipo6.aulasUnla.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docentes")
@CrossOrigin(origins = "http://localhost:3000") // Permitir solicitudes desde localhost:3000

public class DocenteController {

    @Autowired
    private IDocenteService docenteService;

    @GetMapping("/")
    public ResponseEntity<Object> obtenerDocentes(){
        try{
            List<DocenteDTOResponse> listaDto = docenteService.obtenerDocentes();
            return ResponseEntity.status(HttpStatus.OK).body(listaDto);
        } catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("altaDocente")
    public ResponseEntity<Object> altaDocente(@RequestBody DocenteDTORequest dto){
        try{
            docenteService.crearDocente(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Docente creado exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("altaDocentes")
    public ResponseEntity<Object> altaDocentes(@RequestBody List<DocenteDTORequest> dtos){
        try{
            docenteService.crearDocentes(dtos);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Docentes creados exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }



}
