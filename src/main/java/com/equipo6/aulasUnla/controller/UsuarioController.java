package com.equipo6.aulasUnla.controller;

import com.equipo6.aulasUnla.dtos.request.UsuarioDTORequest;
import com.equipo6.aulasUnla.services.IUsuarioService;
import com.equipo6.aulasUnla.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:3000") // Permitir solicitudes desde localhost:3000
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;


    //Este controlador recibe un body desde el front y lo convierte a UsuarioDTORequest
    //Luego convierte el dto a entidad para guardarlo en la base de datos
    //Finalmente devuelve un body para que el front puede saber a través de una msj si el
    //usuario fue ingresado correctamente
    @PostMapping("/registro")
    public ResponseEntity<Object> crearUsuario(@RequestBody UsuarioDTORequest dto){
        try{
            usuarioService.crearUsuario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Usuario creado exitosamente"));
            //ES EQUIVALENTE
            //return new ResponseEntity<>(new Mensaje("Usuario creado exitosamente"), HttpStatus.CREATED );

        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
            //ES EQUIVALENTE
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
        }
    }
}
