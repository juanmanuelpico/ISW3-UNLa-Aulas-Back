package com.equipo6.aulasUnla.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AulaDTOResponse {
    
    
    private int id;

    
    private int numero;

    
    private int capacidad;

  
    private boolean proyector;

 
    private boolean ventilador;

  
    private boolean estufa;

   
    private boolean ocupadoTM;

 
    private boolean ocupadoTN;

 
    private String tipoDeAula;

}
