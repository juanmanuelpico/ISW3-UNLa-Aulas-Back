package com.equipo6.aulasUnla.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity

@Table(name="profesor")
@PrimaryKeyJoinColumn(name = "id_persona")
@DiscriminatorValue("profesor")
public class Profesor extends Persona {

    @Column(name = "legajo")
    private String legajo;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @OneToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;
}