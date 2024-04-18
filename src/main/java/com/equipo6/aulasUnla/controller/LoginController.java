package com.equipo6.aulasUnla.controller;

import com.equipo6.aulasUnla.dtos.request.UsuarioDTOLogin;
import com.equipo6.aulasUnla.dtos.response.UsuarioDTOResponse;
//import com.equipo6.aulasUnla.security.jwt.TokenUtils;
import com.equipo6.aulasUnla.services.IUsuarioService;
import com.equipo6.aulasUnla.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")

public class LoginController {
    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("")
    public ResponseEntity<Object> login(@RequestBody UsuarioDTOLogin dtoLogin){
        try{
            UsuarioDTOResponse response = usuarioService.traerUsuarioLogin(dtoLogin);
            // Generar un nuevo token JWT
            //String nuevoToken = TokenUtils.createToken(response.getUsuario());
            //response.setToken(nuevoToken);
            // Devolver el nuevo token junto con los atributos del usuario en la respuesta
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
