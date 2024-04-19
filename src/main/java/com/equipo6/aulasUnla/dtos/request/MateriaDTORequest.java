package com.equipo6.aulasUnla.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MateriaDTORequest {
    
    private int id;
   
    private String nombre;

    private String turno; //Indicar TM(turno mañana) o TN(turno nocturno)

    private int anioPertenece; //se indicar entre 1ero y 5to año (esta hardcodeada entre 1 y 5)

}
