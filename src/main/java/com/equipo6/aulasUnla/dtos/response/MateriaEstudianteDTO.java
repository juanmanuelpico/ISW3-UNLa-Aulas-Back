package com.equipo6.aulasUnla.dtos.response;

import java.time.LocalDate;

import com.equipo6.aulasUnla.dtos.EstudianteDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MateriaEstudianteDTO {

    private EstudianteDTO estudiante;
    private LocalDate fechaInscripcion;
}
