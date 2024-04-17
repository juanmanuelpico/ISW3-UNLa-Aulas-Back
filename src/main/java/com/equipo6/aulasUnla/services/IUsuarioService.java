package com.equipo6.aulasUnla.services;

import com.equipo6.aulasUnla.dtos.request.UsuarioDTOLogin;
import com.equipo6.aulasUnla.dtos.request.UsuarioDTORequest;
import com.equipo6.aulasUnla.dtos.response.UsuarioDTOResponse;
import com.equipo6.aulasUnla.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public boolean crearUsuario(UsuarioDTORequest dto) throws Exception;

    public UsuarioDTOResponse traerUsuarioLogin(UsuarioDTOLogin dtoLogin)throws Exception;

}
