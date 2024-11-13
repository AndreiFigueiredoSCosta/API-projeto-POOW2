package com.example.demo.controller;

import com.example.demo.dto.ProdutoDto;
import com.example.demo.model.Produto.Produto;
import com.example.demo.model.categoria.Categoria;
import com.example.demo.model.fornecedor.Fornecedor;
import com.example.demo.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/listar-produtos")
    public List<Produto> listarProdutos() {
        return service.listarProdutos();
    }

    @GetMapping("/listar-produto/{id}")
    public Produto listarProduto(@PathVariable UUID id){
        return this.service.listarProduto(id);
    }

    @GetMapping("/listar-fornecedor-do-produto/{id}")
    public List<Fornecedor> listarFornecedorDoProduto(@PathVariable UUID id) {
        return this.service.listarFornecedoresDoProduto(id);
    }

    @Transactional
    @PostMapping("/adicionar")
    public ResponseEntity<Produto> adicionar(@RequestBody @Valid ProdutoDto produto, UriComponentsBuilder uriBuilder){
        Produto p = this.service.adicionarProduto(produto);
        URI uri = uriBuilder.path("/produto/{uuid}").buildAndExpand(p.getIdProduto()).toUri();
        return ResponseEntity.created(uri).body(p);
    }

    @Transactional
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Produto> deletar(@PathVariable UUID id) {
        this.service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/atualizar")
    public ResponseEntity<Produto> atualizar(@RequestBody @Valid Produto produto) {
        this.service.atualizarProduto(produto);
        return ResponseEntity.ok(produto);
    }
}
