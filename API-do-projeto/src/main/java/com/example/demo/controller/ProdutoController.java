package com.example.demo.controller;

import com.example.demo.dto.ProdutoDto;
import com.example.demo.model.Produto.Produto;
import com.example.demo.model.categoria.Categoria;
import com.example.demo.model.fornecedor.Fornecedor;
import com.example.demo.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Produtos", description = "Paths das operações dos produtos")
public class ProdutoController {

    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/listar-produtos")
    @Operation(summary = "Listar todos os produtos",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Lista de produtos",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Produto.class)))
            })
    public List<Produto> listarProdutos() {
        return service.listarProdutos();
    }

    @GetMapping("/listar-produto/{id}")
    @Operation(summary = "Buscar produto por ID",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Produto encontrado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Produto.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Produto não encontrado",
                            content = @Content(mediaType = "application/json"))
            })
    public Produto listarProduto(@PathVariable UUID id){
        return this.service.listarProduto(id);
    }

    @GetMapping("/listar-fornecedor-do-produto/{id}")
    @Operation(summary = "Listar fornecedores de um produto específico",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Lista de fornecedores do produto",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Fornecedor.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Produto não encontrado",
                            content = @Content(mediaType = "application/json"))
            })
    public List<Fornecedor> listarFornecedorDoProduto(@PathVariable UUID id) {
        return this.service.listarFornecedoresDoProduto(id);
    }

    @Transactional
    @PostMapping("/adicionar")
    @Operation(summary = "Adicionar um novo produto",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Produto criado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Produto.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Dados inválidos fornecidos para o produto",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity<Produto> adicionar(@RequestBody @Valid ProdutoDto produto, UriComponentsBuilder uriBuilder){
        Produto p = this.service.adicionarProduto(produto);
        URI uri = uriBuilder.path("/produto/{uuid}").buildAndExpand(p.getIdProduto()).toUri();
        return ResponseEntity.created(uri).body(p);
    }

    @Transactional
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar um produto por ID",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "Produto deletado com sucesso"),
                    @ApiResponse(responseCode = "404",
                            description = "Produto não encontrado",
                            content = @Content(mediaType = "application/json"))
            })

    public ResponseEntity<Produto> deletar(@PathVariable UUID id) {
        this.service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/atualizar")
    @Operation(summary = "Atualizar um produto",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Produto atualizado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Produto.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Dados inválidos fornecidos para o produto",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity<Produto> atualizar(@RequestBody @Valid Produto produto) {
        this.service.atualizarProduto(produto);
        return ResponseEntity.ok(produto);
    }
}
