package com.example.demo.controller;

import com.example.demo.model.Produto.Produto;
import com.example.demo.model.categoria.Categoria;
import com.example.demo.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Categorias", description = "Paths das operações das categorias")
public class CategoriaController {
    private CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service=service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todas as categorias",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Lista de categorias",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Categoria.class)))
            })
    public List<Categoria> listar(){
        return this.service.listar();
    }

    @GetMapping("/listar-categoria/{id}")
    @Operation(summary = "Buscar categoria por ID",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Categoria encontrada",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Categoria.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Categoria não encontrada",
                            content = @Content(mediaType = "application/json"))
            })
    public Categoria listarCategoria(@PathVariable UUID id){
        return this.service.listarCategoria(id);
    }

    @GetMapping("/listar-produtos-da-categoria/{id}")
    @Operation(summary = "Listar produtos de uma categoria específica",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Lista de produtos da categoria",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Produto.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Categoria não encontrada",
                            content = @Content(mediaType = "application/json"))
            })
    public List<Produto> listarProdutosDaCategoria(@PathVariable UUID id){
        return this.service.listarProdutosDaCategoria(id);
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adicionar uma nova categoria",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Categoria criada com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Categoria.class)))
            })
    public ResponseEntity<Categoria> adicionar(@RequestBody @Valid Categoria categoria, UriComponentsBuilder uriBuilder){
        this.service.salvar(categoria);
        URI uri = uriBuilder.path("/categoria/{uuid}").buildAndExpand(categoria.getIdCategoria()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @Transactional
    @DeleteMapping("/deletar/{idCategoria}")
    @Operation(summary = "Deletar uma categoria",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "Categoria deletada com sucesso"),
                    @ApiResponse(responseCode = "404",
                            description = "Categoria não encontrada",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity deletar(@PathVariable UUID idCategoria){
        this.service.deletar(idCategoria);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar uma categoria",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Categoria atualizada com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Categoria.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Categoria não encontrada",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity atualizar(@RequestBody @Valid Categoria categoria){
        this.service.atualizar(categoria);
        return ResponseEntity.ok(categoria);
    }
}
