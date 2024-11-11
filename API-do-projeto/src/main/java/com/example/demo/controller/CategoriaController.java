package com.example.demo.controller;

import com.example.demo.model.categoria.Categoria;
import com.example.demo.service.CategoriaService;
import jakarta.transaction.Transactional;
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

    @PostMapping("/adicionar")
    public ResponseEntity<Categoria> adicionar(@RequestBody Categoria categoria, UriComponentsBuilder uriBuilder){
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
    public ResponseEntity atualizar(@RequestBody Categoria categoria){
        this.service.atualizar(categoria);
        return ResponseEntity.ok(categoria);
    }
}
