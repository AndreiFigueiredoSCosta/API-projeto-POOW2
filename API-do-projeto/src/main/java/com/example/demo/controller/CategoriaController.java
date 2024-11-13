package com.example.demo.controller;

import com.example.demo.model.Produto.Produto;
import com.example.demo.model.categoria.Categoria;
import com.example.demo.service.CategoriaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service=service;
    }

    @GetMapping("/listar")
    public List<Categoria> listar(){
        return this.service.listar();
    }

    @GetMapping("/listar-categoria/{id}")
    public Categoria listarCategoria(@PathVariable UUID id){
        return this.service.listarCategoria(id);
    }

    @GetMapping("/listar-produtos-da-categoria/{id}")
    public List<Produto> listarProdutosDaCategoria(@PathVariable UUID id){
        return this.service.listarProdutosDaCategoria(id);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Categoria> adicionar(@RequestBody @Valid Categoria categoria, UriComponentsBuilder uriBuilder){
        this.service.salvar(categoria);
        URI uri = uriBuilder.path("/categoria/{uuid}").buildAndExpand(categoria.getIdCategoria()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @Transactional
    @DeleteMapping("/deletar/{idCategoria}")
    public ResponseEntity deletar(@PathVariable UUID idCategoria){
        this.service.deletar(idCategoria);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/atualizar")
    public ResponseEntity atualizar(@RequestBody @Valid Categoria categoria){
        this.service.atualizar(categoria);
        return ResponseEntity.ok(categoria);
    }
}
