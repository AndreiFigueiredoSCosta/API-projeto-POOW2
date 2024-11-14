package com.example.demo.controller;

import com.example.demo.dto.MovimentacaoDto;
import com.example.demo.model.Produto.Produto;
import com.example.demo.model.movimentacao.Movimentacao;
import com.example.demo.service.MovimentacaoService;
import com.example.demo.service.ProdutoService;
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
@RequestMapping("/movimentacao")
@Tag(name = "Movimentações", description = "Paths das operações das movimentações")
public class MovimentacaoController {

    private MovimentacaoService movimentacaoService;
    private ProdutoService produtoService;

    public MovimentacaoController(MovimentacaoService ms, ProdutoService ps){
        this.movimentacaoService=ms;
        this.produtoService = ps;
    }

    @GetMapping("/listar-movimentacoes")
    @Operation(summary = "Listar todas as movimentações",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Lista de movimentações",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Movimentacao.class)))
            })
    public List<Movimentacao> listarMovimentacoes(){
        return this.movimentacaoService.listarMovimentacoes();
    }

    @GetMapping("/listar-movimentacao/{id}")
    @Operation(summary = "Buscar movimentação por ID",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Movimentação encontrada",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Movimentacao.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Movimentação não encontrada",
                            content = @Content(mediaType = "application/json"))
            })
    public Movimentacao listarMovimentacao(@PathVariable UUID id){
        return this.movimentacaoService.listarMovimentacao(id);
    }

    @PostMapping("/realizar-movimentacao")
    @Operation(summary = "Realizar uma nova movimentação",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Movimentação realizada com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Movimentacao.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Dados inválidos fornecidos para a movimentação",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity<Movimentacao> realizarMovimentacao(@RequestBody @Valid MovimentacaoDto m, UriComponentsBuilder uriBuilder){
        Movimentacao movimentacao = this.movimentacaoService.realizarMovimentacao(m);
        URI uri = uriBuilder.path("/categoria/{uuid}").buildAndExpand(movimentacao.getIdMovimentacao()).toUri();
        return ResponseEntity.created(uri).body(movimentacao);
    }
}
