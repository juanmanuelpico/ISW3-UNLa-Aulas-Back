package com.equipo6.aulasUnla.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstudianteDTOResponse {

    private String nombre;

    private String apellido;

    private String dni;

    private String email;

    private int cohorte;
}
