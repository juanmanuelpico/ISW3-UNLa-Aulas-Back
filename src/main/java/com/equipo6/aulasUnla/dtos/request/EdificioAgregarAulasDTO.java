package com.equipo6.aulasUnla.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class EdificioAgregarAulasDTO {

    private int idEdificio;

    private List<AulaDTORequest> aulas;
}
