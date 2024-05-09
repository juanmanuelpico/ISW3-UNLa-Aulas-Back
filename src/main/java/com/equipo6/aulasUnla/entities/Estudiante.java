package com.equipo6.aulasUnla.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(mappedBy = "estudiante")
    private Set<MateriaEstudiante> materiaEstudianteList;

}