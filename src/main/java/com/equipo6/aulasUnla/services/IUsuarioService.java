package com.equipo6.aulasUnla.services;

import com.equipo6.aulasUnla.dtos.request.UsuarioDTOLogin;
import com.equipo6.aulasUnla.dtos.request.UsuarioDTORequest;
import com.equipo6.aulasUnla.dtos.response.UsuarioDTOResponse;

public interface IUsuarioService {

    public boolean crearUsuario(UsuarioDTORequest dto) throws Exception;

    public UsuarioDTOResponse traerUsuarioLogin(UsuarioDTOLogin dtoLogin)throws Exception;

}
