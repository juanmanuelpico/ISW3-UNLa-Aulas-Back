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

@Table(name="estudiante")
@PrimaryKeyJoinColumn(name = "id_persona")
@DiscriminatorValue("estudiante")
public class Estudiante extends Persona{

    @Column(name = "cohorte")
    private int cohorte;

    @ManyToMany
    @JoinTable(name = "estudiante_tiene_materia", joinColumns = @JoinColumn(name = "id_estudiante"),
            inverseJoinColumns = @JoinColumn(name = "id_tarea"))
    private Set<Materia> materias = new HashSet<>();

}
