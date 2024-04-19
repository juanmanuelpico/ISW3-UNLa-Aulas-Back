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

@Table(name="materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_materia")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cant_estudiantes")
    private int cantEstudiantes;

    @Column(name = "turno")
    private String turno; //Indicar TM(turno mañana) o TN(turno nocturno)

    @Column(name = "aula_asignada")
    private boolean aulaAsignada;

    @Column(name = "anio_pertenece")
    private int anioPertenece; //se indicar entre 1ero y 5to año (esta hardcodeada entre 1 y 5)

    @OneToOne(mappedBy = "materia")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "id_aula")
    private Aula aula;

    @ManyToMany
    @JoinTable(name = "materia_tiene_estudiante", joinColumns = @JoinColumn(name = "id_materia"),
            inverseJoinColumns = @JoinColumn(name = "id_estudiante"))
    private Set<Estudiante> estudiantes = new HashSet<>();

}
