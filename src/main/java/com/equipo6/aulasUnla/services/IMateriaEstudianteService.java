package com.equipo6.aulasUnla.services;

import com.equipo6.aulasUnla.dtos.request.MateriaAsignarUsuariosDTO;

public interface IMateriaEstudianteService {

    public void asignarEstudianteAMateria(String nombreMateria, int idEstudiante) throws Exception;

    public void asignarEstudiantesAMateria(MateriaAsignarUsuariosDTO dto) throws Exception;
}
