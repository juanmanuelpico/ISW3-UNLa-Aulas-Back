package com.equipo6.aulasUnla.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AulaDTORequest {

  

    private int numero;

    private int capacidad;

    private boolean proyector;

    private boolean ventilador;

    private boolean estufa;

  //se setea false por default
  //  private boolean ocupadoTM;
  //se setea false por default
 // private boolean ocupadoTN;
 
    private String tipoDeAula; // Se indica si es "Tradicional" o "Laboratorio"
 
    private int idEdificio;

}
    

