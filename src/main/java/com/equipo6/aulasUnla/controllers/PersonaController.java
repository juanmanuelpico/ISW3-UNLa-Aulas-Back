package com.equipo6.aulasUnla.controllers;

import com.equipo6.aulasUnla.entities.Persona;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @GetMapping("/traer")
    public String traer(){
        return "hola";
    }
}
