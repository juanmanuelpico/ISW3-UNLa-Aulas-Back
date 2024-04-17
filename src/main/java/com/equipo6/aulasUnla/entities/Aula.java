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

@Table(name="aula")

public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_aula")
    private int id;

    @Column(name="numero")
    private int numero;

    @Column(name="capacidad")
    private int capacidad;

    @Column(name="proyector")
    private boolean proyector;

    @Column(name="ventilador")
    private boolean ventilador;

    @Column(name="estufa")
    private boolean estufa;

    @Column(name="ocupado_TM")
    private boolean ocupadoTM;

    @Column(name="ocupado_TN")
    private boolean ocupadoTN;

    @Column(name="tipo_de_aula")
    private String tipoDeAula;

    @OneToMany(mappedBy = "aula")
    private Set<Materia> materias = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_edificio")
    private Edificio edificio;

}
