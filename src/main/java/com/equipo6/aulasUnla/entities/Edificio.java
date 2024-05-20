package com.equipo6.aulasUnla.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity

@Table(name="edificio")
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_edificio")
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="cant_aulas")
    private int cantAulas;

    @Column(name="cant_banios")
    private int cantBanios;

    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    private Set<Aula> aulas = new HashSet<>();

}
