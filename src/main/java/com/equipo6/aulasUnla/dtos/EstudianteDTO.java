package com.equipo6.aulasUnla.dtos;

import com.equipo6.aulasUnla.entities.Persona;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstudianteDTO extends Persona{

    private int cohorte;

}
