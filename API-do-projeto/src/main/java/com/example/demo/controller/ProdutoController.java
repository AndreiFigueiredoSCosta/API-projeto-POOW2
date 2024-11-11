package com.example.demo.controller;

import com.example.demo.model.Produto.Produto;
import com.example.demo.model.categoria.Categoria;
import com.example.demo.model.fornecedor.Fornecedor;
import com.example.demo.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class ProdutoController {

    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/listar-produtos")
    public List<Produto> listarProdutos() {
        return service.listarProdutos();
    }

    @GetMapping("/listar-fornecedor-do-produto/{id}")
    public List<Fornecedor> listarFornecedorDoProduto(@PathVariable UUID id) {
        return this.service.listarFornecedoresDoProduto(id);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Produto> adicionar(@RequestBody Produto produto, UriComponentsBuilder uriBuilder){
        this.service.adicionarProduto(produto);
        URI uri = uriBuilder.path("/categoria/{uuid}").buildAndExpand(produto.getIdProduto()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Produto> deletar(@PathVariable UUID id) {
        this.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/atualizar")
    public ResponseEntity<Produto> atualizar(@RequestBody Produto produto) {
        this.service.atualizarProduto(produto);
        return ResponseEntity.ok(produto);
    }
}
