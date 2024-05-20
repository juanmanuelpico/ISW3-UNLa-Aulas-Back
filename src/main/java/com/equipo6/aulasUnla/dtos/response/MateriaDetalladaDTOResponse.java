package com.equipo6.aulasUnla.dtos.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MateriaDetalladaDTOResponse extends MateriaDTOResponse {
    private List<MateriaEstudianteDTO> materiaEstudianteList = new ArrayList<>();
}
