package com.compass.desafio.repositories;

import com.compass.desafio.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<List<Cliente>> findByNome(String nome);

}