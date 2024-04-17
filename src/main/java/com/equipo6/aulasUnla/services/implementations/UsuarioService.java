package com.equipo6.aulasUnla.services.implementations;

import com.equipo6.aulasUnla.dtos.request.UsuarioDTOLogin;
import com.equipo6.aulasUnla.dtos.request.UsuarioDTORequest;
import com.equipo6.aulasUnla.dtos.response.UsuarioDTOResponse;
import com.equipo6.aulasUnla.entities.Usuario;
import com.equipo6.aulasUnla.repositories.UsuarioRepository;
import com.equipo6.aulasUnla.services.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("usuarioService")
public class UsuarioService implements IUsuarioService {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    @Override
    public boolean crearUsuario(UsuarioDTORequest dto) throws Exception {

        if (usuarioRepository.findByUsuario(dto.getUsuario()) != null) {
            throw new Exception("El nombre del usuario ya existe");
        }
        if (usuarioRepository.findByEmail(dto.getEmail()) != null) {
            throw new Exception("El mail ya se encuentra registrado");
        }

        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setFechaAlta(LocalDate.now());
        usuario.setActivo(true);

        // Hash de la contraseña
        usuario.setPassword(passwordEncoder().encode(dto.getPassword()));
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public UsuarioDTOResponse traerUsuarioLogin (UsuarioDTOLogin dtoLogin) throws Exception {
        Usuario usuarioEntidad = usuarioRepository.findByUsuario(dtoLogin.getUsuario());
        if (usuarioEntidad == null) {
            throw new Exception("Usuario y/o contraseña incorrecto");
        }
        // Utilizar BCryptPasswordEncoder para comparar la contraseña en texto plano
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(dtoLogin.getPassword(), usuarioEntidad.getPassword())) {
            throw new Exception("Usuario y/o contraseña incorrecto");
        }
        return modelMapper.map(usuarioEntidad, UsuarioDTOResponse.class);
    }

}
