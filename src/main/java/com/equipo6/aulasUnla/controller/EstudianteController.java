package com.equipo6.aulasUnla.controller;

import com.equipo6.aulasUnla.dtos.request.EstudianteDTORequest;
import com.equipo6.aulasUnla.dtos.response.EstudianteDTOResponse;
import com.equipo6.aulasUnla.services.IEstudianteService;
import com.equipo6.aulasUnla.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
@CrossOrigin(origins = "http://localhost:3000") // Permitir solicitudes desde localhost:3000
public class EstudianteController {

    @Autowired
    private IEstudianteService estudianteService;

    @PostMapping("altaEstudiante")
    public ResponseEntity<Object> altaEstudiante(@RequestBody EstudianteDTORequest dto){
        try{
            estudianteService.crearEstudiante(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Estudiante creado exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("altaEstudiantes")
    public ResponseEntity<Object> altaEstudiantes(@RequestBody List<EstudianteDTORequest> dtos){
        try{
            estudianteService.crearEstudiantes(dtos);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Estudiantes creados exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("traerEstudiantes")
    public ResponseEntity<Object> obtenerEstudiantes(){
        try{
            List<EstudianteDTOResponse> listaDto = estudianteService.obtenerEstudiantes();
            return ResponseEntity.status(HttpStatus.OK).body(listaDto);
        } catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }
}
