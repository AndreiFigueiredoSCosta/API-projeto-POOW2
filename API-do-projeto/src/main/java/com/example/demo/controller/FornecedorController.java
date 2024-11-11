package com.example.demo.controller;

import com.example.demo.model.Produto.Produto;
import com.example.demo.model.fornecedor.Fornecedor;
import com.example.demo.service.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    private FornecedorService service;

    public FornecedorController(FornecedorService service){
        this.service=service;
    }

    @GetMapping("/listar-fornecedores")
    public List<Fornecedor> listar(){
        return this.service.ListarFornecedores();
    }

    @GetMapping("/listar-produtos-do-fornecedor/{id}")
    public List<Produto> listarProdutosDoFornecedor(@PathVariable UUID id){
        return this.service.listarProdutosDoFornecedor(id);
    }

    @PutMapping("/adicionar")
    public ResponseEntity adicionarFornecedor(@RequestBody Fornecedor fornecedor, UriComponentsBuilder uriBuilder){
        this.service.salvar(fornecedor);
        URI uri = uriBuilder.path("/fornecedor/{uuid}").buildAndExpand(fornecedor.getIdFornecedor()).toUri();
        return  ResponseEntity.created(uri).body(fornecedor);
    }
}
