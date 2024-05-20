package com.equipo6.aulasUnla.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EdificioDTOResponse {

    int id;

    int cantidadAulas;

    int cantBanios;

    String nombre;
}
