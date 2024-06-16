package com.equipo6.aulasUnla.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Column(name = "dia")
    private String dia;

    @Column(name = "aula_asignada")
    private boolean aulaAsignada;

    @Column(name = "anio_pertenece")
    private int anioPertenece; //se indicar entre 1ero y 5to año (esta hardcodeada entre 1 y 5)

    @OneToOne(mappedBy = "materia")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "id_aula")
    private Aula aula;

    @OneToMany(mappedBy = "materia")
    private Set<MateriaEstudiante> materiaEstudianteList;

}