package com.compass.desafio.controllers;

import com.compass.desafio.entities.Cidade;
import com.compass.desafio.entities.Cliente;
import com.compass.desafio.repositories.CidadeRepository;
import com.compass.desafio.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final String SUCESSO_CLIENTE_REGISTRADO = "Cliente registrado com sucesso.";
    private final String SUCESSO_CLIENTE_DELETADO = "Cliente deletado com sucesso.";
    private final String SUCESSO_CLIENTE_NOME_ALTERADO = "Nome do cliente alterado com sucesso.";
    private final String ERRO_CIDADE_NAO_REGISTRADA = "Cidade não registrada.";
    private final String ERRO_CIDADE = "Cliente não registrado. Verifique a requisição.";
    private final String ERRO_BUSCAR_CLIENTE_PARAMETROS = "Cliente não encontrado pelo(s) parâmetro(s).";

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @PostMapping
    public ResponseEntity postCliente(@RequestBody Cliente cliente) {

        HttpStatus retornoStatus;
        String retornoBody;

        if (cliente.getCidade() != null) {
            Optional<Cidade> cidadeOpt = this.cidadeRepository.findByNomeAndEstado(cliente.getCidade().getNome(), cliente.getCidade().getEstado());

            if (!cidadeOpt.isPresent()) {
                retornoBody = ERRO_CIDADE_NAO_REGISTRADA;
                retornoStatus = HttpStatus.BAD_REQUEST;
            } else {
                cliente.getCidade().setId(cidadeOpt.get().getId());
                this.clienteRepository.save(cliente);
                retornoBody = SUCESSO_CLIENTE_REGISTRADO;
                retornoStatus = HttpStatus.CREATED;
            }
        } else {
            retornoBody = ERRO_CIDADE;
            retornoStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(retornoBody, retornoStatus);
    }

    @GetMapping
    public ResponseEntity getClienteByName(@RequestParam("nome") String nomeCliente) {

        HttpStatus retornoStatus;
        Object retornoBody;

        Optional<List<Cliente>> optionalClienteList = this.clienteRepository.findByNome(nomeCliente);

        if (optionalClienteList.isPresent()) {
            retornoBody = optionalClienteList.get();
            retornoStatus = HttpStatus.OK;
        } else {
            retornoBody = ERRO_BUSCAR_CLIENTE_PARAMETROS;
            retornoStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(retornoBody, retornoStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity getClienteById(@PathVariable("id") Long idCliente) {

        HttpStatus retornoStatus;
        Object retornoBody;

        Optional<Cliente> optionalCliente = this.clienteRepository.findById(idCliente);

        if (optionalCliente.isPresent()) {
            retornoBody = optionalCliente.get();
            retornoStatus = HttpStatus.OK;
        } else {
            retornoBody = ERRO_BUSCAR_CLIENTE_PARAMETROS;
            retornoStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(retornoBody, retornoStatus);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClienteById(@PathVariable("id") Long idCliente) {

        HttpStatus retornoStatus;
        String retornoBody;

        Optional<Cliente> optionalCliente = this.clienteRepository.findById(idCliente);

        if (optionalCliente.isPresent()) {
            this.clienteRepository.delete(optionalCliente.get());
            retornoBody = SUCESSO_CLIENTE_DELETADO;
            retornoStatus = HttpStatus.OK;
        } else {
            retornoBody = ERRO_BUSCAR_CLIENTE_PARAMETROS;
            retornoStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(retornoBody, retornoStatus);

    }

    @PatchMapping("/{id}")
    public ResponseEntity alterarNomeClienteById(@RequestBody Cliente cliente, @PathVariable("id") Long idCliente) {

        HttpStatus retornoStatus;
        String retornoBody;

        Optional<Cliente> optionalCliente = this.clienteRepository.findById(idCliente);

        if (optionalCliente.isPresent()) {
            optionalCliente.get().setNome(cliente.getNome());
            this.clienteRepository.save(optionalCliente.get());
            retornoBody = SUCESSO_CLIENTE_NOME_ALTERADO;
            retornoStatus = HttpStatus.OK;
        } else {
            retornoBody = ERRO_BUSCAR_CLIENTE_PARAMETROS;
            retornoStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(retornoBody, retornoStatus);

    }

}
