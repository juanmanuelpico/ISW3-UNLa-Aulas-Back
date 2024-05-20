package com.equipo6.aulasUnla.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

@Table(name="docente")
@PrimaryKeyJoinColumn(name = "id_profesor")
@DiscriminatorValue("profesor")
public class Docente extends Persona {

    @Column(name = "legajo")
    private String legajo;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @OneToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;
}
