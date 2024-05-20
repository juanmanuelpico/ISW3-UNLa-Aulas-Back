package com.equipo6.aulasUnla.repositories;

import com.equipo6.aulasUnla.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findById(int id);

    Usuario findByUsuario(String usuario);

    Optional<Usuario> findOneByUsuario(String usuario);

    Usuario findByEmail(String email);

    @Query(value = "SELECT * FROM usuario u WHERE BINARY u.usuario = :usuario AND u.password = :password", nativeQuery = true)
    Usuario findByUsuarioAndPasswordCaseSensitive(@Param("usuario") String usuario, @Param("password") String password);
}
