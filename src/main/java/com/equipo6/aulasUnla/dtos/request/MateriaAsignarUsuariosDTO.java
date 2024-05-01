package com.equipo6.aulasUnla.dtos.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

//este dto recibe el nombre de la materia y un listado de ids de usuarios a agregar
public class MateriaAsignarUsuariosDTO {

    private String nombreMateria;

    private String turno;

    private List<Integer> idEstudiantes;
}
