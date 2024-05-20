package com.equipo6.aulasUnla.repositories;

import com.equipo6.aulasUnla.entities.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocenteRepository  extends JpaRepository<Docente, Integer> {

    Docente findById(int id);

    Docente findByDni(String dni);

    Docente findByEmail(String email);

    List<Docente> findAll();

}
