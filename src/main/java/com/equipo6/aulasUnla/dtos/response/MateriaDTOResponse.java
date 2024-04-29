package com.equipo6.aulasUnla.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MateriaDTOResponse {

    private String nombre;

    private String turno;

    private int anioPertenece;

    private int cantidadEstudiantes;

    private String docenteACargo;

    private int aulaAsignada;

    private String edificio;
}
