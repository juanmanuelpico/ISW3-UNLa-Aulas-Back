package com.equipo6.aulasUnla.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DocenteDTOResponse {

    private String nombre;

    private String apellido;

    private String dni;

    private String email;

    private String legajo;

    private LocalDate fechaIngreso;
}
