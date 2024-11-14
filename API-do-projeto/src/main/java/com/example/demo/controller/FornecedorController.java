package com.example.demo.controller;

import com.example.demo.model.Produto.Produto;
import com.example.demo.model.fornecedor.Fornecedor;
import com.example.demo.service.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fornecedor")
@Tag(name = "Fornecedor", description = "Paths das operações dos fornecedores")
public class FornecedorController {

    private FornecedorService service;

    public FornecedorController(FornecedorService service){
        this.service=service;
    }

    @GetMapping("/listar-fornecedores")
    @Operation(summary = "Listar todos os fornecedores",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Lista de fornecedores",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Fornecedor.class)))
            })
    public List<Fornecedor> listar(){
        return this.service.ListarFornecedores();
    }

    @GetMapping("/listar-fornecedor/{id}")
    @Operation(summary = "Buscar fornecedor por ID",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Fornecedor encontrado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Fornecedor.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Fornecedor não encontrado",
                            content = @Content(mediaType = "application/json"))
            })
    public Fornecedor listarFornecedor(@PathVariable UUID id){
        return this.service.litarFornecedor(id);
    }

    @GetMapping("/listar-produtos-do-fornecedor/{id}")
    @Operation(summary = "Listar produtos de um fornecedor específico",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Lista de produtos do fornecedor",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Produto.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Fornecedor não encontrado",
                            content = @Content(mediaType = "application/json"))
            })
    public List<Produto> listarProdutosDoFornecedor(@PathVariable UUID id){
        return this.service.listarProdutosDoFornecedor(id);
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adicionar um novo fornecedor",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Fornecedor criado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Fornecedor.class)))
            })
    public ResponseEntity adicionarFornecedor(@RequestBody @Valid Fornecedor fornecedor, UriComponentsBuilder uriBuilder){
        this.service.salvar(fornecedor);
        URI uri = uriBuilder.path("/fornecedor/{uuid}").buildAndExpand(fornecedor.getIdFornecedor()).toUri();
        return  ResponseEntity.created(uri).body(fornecedor);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar um fornecedor",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "Fornecedor deletado com sucesso"),
                    @ApiResponse(responseCode = "404",
                            description = "Fornecedor não encontrado",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity deletarFornecedor(@PathVariable UUID id){
        this.service.deletar(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar um fornecedor",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Fornecedor atualizado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Fornecedor.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Fornecedor não encontrado",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity atualizarFornecedor(@RequestBody @Valid Fornecedor fornecedor){
        this.service.atualizar(fornecedor);
        return  ResponseEntity.ok(fornecedor);
    }
}
