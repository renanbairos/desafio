package com.compass.desafio.repositories;

import com.compass.desafio.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    Optional<List<Cidade>> findByNome(String nome);

    Optional<List<Cidade>> findByEstado(String estado);

    Optional<Cidade> findByNomeAndEstado(String nome, String estado);
}