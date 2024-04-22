package com.equipo6.aulasUnla.services;

import java.util.List;

import com.equipo6.aulasUnla.dtos.request.MateriaAsignarUsuariosDTO;


import com.equipo6.aulasUnla.dtos.request.MateriaDTORequest;
import com.equipo6.aulasUnla.dtos.response.MateriaDTOResponse;
import com.equipo6.aulasUnla.entities.Materia;

public interface IMateriaService {
    public Materia obtenerMateria(String nombre) throws Exception;

    public boolean crearMateria(MateriaDTORequest dto) throws Exception;

    public boolean crearMaterias(List<MateriaDTORequest> dtos) throws Exception;

    public boolean agregarEstudiante(String materia, int idEstudiante) throws Exception;

    public boolean agregarEstudiante(Materia materia, int idEstudiante) throws Exception;

    public boolean agregarEstudiantes(MateriaAsignarUsuariosDTO dto) throws Exception;

    public List<MateriaDTOResponse> obtenerMaterias() throws Exception;

    public List<MateriaDTOResponse> obtenerMateriasPorAnio(int anio) throws Exception;

   
   
}
