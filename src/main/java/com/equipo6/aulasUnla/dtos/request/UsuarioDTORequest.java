package com.equipo6.aulasUnla.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTORequest {

    private int id;

    private String usuario;

    private String password;

    private String nombre;

    private String apellido;

    private String email;

}
