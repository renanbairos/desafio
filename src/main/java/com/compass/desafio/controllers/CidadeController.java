package com.compass.desafio.controllers;

import com.compass.desafio.entities.Cidade;
import com.compass.desafio.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    private final String SUCESSO_CIDADE_CRIADA = "Cidade criada com sucesso.";
    private final String ERRO_CIDADE_CADASTRADA = "Cidade já cadastrada.";

    @Autowired
    private CidadeRepository cidadeRepository;

    @PostMapping
    public ResponseEntity postCidade(@RequestBody Cidade cidade) {

        HttpStatus retornoStatus;
        String retornoBody;

        Optional<Cidade> cidadeOpt = this.cidadeRepository.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());

        if (cidadeOpt.isPresent()) {

            retornoStatus = HttpStatus.BAD_REQUEST;
            retornoBody = ERRO_CIDADE_CADASTRADA;

        } else {

            this.cidadeRepository.save(cidade);
            retornoStatus = HttpStatus.CREATED;
            retornoBody = SUCESSO_CIDADE_CRIADA;

        }

        return new ResponseEntity(retornoBody, retornoStatus);
    }

    @GetMapping("/nome")
    public @ResponseBody List<Cidade> getCidadeByName(@RequestParam("nome") String nomeCidade) {

        List<Cidade> retornoCidades = new ArrayList<>();
        Optional<List<Cidade>> optionalCidadeList = this.cidadeRepository.findByNome(nomeCidade);

        if (optionalCidadeList.isPresent()) {
            retornoCidades = optionalCidadeList.get();
        }

        return retornoCidades;
    }

    @GetMapping("/estado")
    public @ResponseBody List<Cidade> getCidadeByEstado(@RequestParam("sigla") String siglaEstado) {

        List<Cidade> retornoCidades = new ArrayList<>();
        Optional<List<Cidade>> optionalCidadeList = this.cidadeRepository.findByEstado(siglaEstado);

        if (optionalCidadeList.isPresent()) {
            retornoCidades = optionalCidadeList.get();
        }

        return retornoCidades;

    }

}
