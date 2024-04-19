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
