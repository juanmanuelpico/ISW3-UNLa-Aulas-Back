package com.equipo6.aulasUnla.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DocenteDTORequest {

    private int id;

    private String nombre;

    private String apellido;

    private String dni;

    private String email;

}
