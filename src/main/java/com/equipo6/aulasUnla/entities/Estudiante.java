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
@PrimaryKeyJoinColumn(name = "id_estudiante")
@DiscriminatorValue("estudiante")
public class Estudiante extends Persona{

    @Column(name = "cohorte")
    private int cohorte;

    @ManyToMany(mappedBy = "estudiantes")
    private Set<Materia> materias = new HashSet<>();

}
