package com.equipo6.aulasUnla.services;

import java.util.List;


import com.equipo6.aulasUnla.dtos.request.MateriaAsignarDocenteDTO;
import com.equipo6.aulasUnla.dtos.request.MateriaDTORequest;
import com.equipo6.aulasUnla.dtos.response.MateriaDTOResponse;
import com.equipo6.aulasUnla.entities.Materia;

public interface IMateriaService {
    List<MateriaDTOResponse> obtenerMateriasPorAnioConDocenteAulaEdificio(int anio) throws Exception;

    public Materia obtenerMateria(String nombre, String turno) throws Exception;

    public Materia obtenerMateria(String nombre) throws Exception;

    public boolean crearMateria(MateriaDTORequest dto) throws Exception;

    public boolean crearMaterias(List<MateriaDTORequest> dtos) throws Exception;

    public boolean asignarDocenteAMateria(MateriaAsignarDocenteDTO dto) throws Exception;

    public List<MateriaDTOResponse> obtenerMaterias() throws Exception;

    public List<MateriaDTOResponse> obtenerMateriasPorAnio(int anio) throws Exception;

    public void actualizarCantEstudiantes(Materia materia) throws Exception;

}
