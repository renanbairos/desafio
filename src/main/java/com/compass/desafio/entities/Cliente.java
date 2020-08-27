package com.compass.desafio.entities;

import com.compass.desafio.util.Sexo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Sexo sexo;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    private int idade;

    @PrePersist
    public void prePersist() {
        this.idade = LocalDate.now().getYear() - this.dataNascimento.getYear();
    }

    @OneToOne
    private Cidade cidade;

}