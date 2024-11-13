package com.example.demo.controller;

import com.example.demo.dto.MovimentacaoDto;
import com.example.demo.model.Produto.Produto;
import com.example.demo.model.movimentacao.Movimentacao;
import com.example.demo.service.MovimentacaoService;
import com.example.demo.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/movimentacao")
@RestController
public class MovimentacaoController {

    private MovimentacaoService movimentacaoService;
    private ProdutoService produtoService;

    public MovimentacaoController(MovimentacaoService ms, ProdutoService ps){
        this.movimentacaoService=ms;
        this.produtoService = ps;
    }

    @GetMapping("/listar-movimentacoes")
    public List<Movimentacao> listarMovimentacoes(){
        return this.movimentacaoService.listarMovimentacoes();
    }

    @GetMapping("/listar-movimentacao/{id}")
    public Movimentacao listarMovimentacao(@PathVariable UUID id){
        return this.movimentacaoService.listarMovimentacao(id);
    }

    @PostMapping("/realizar-movimentacao")
    public ResponseEntity<Movimentacao> realizarMovimentacao(@RequestBody @Valid MovimentacaoDto m, UriComponentsBuilder uriBuilder){
        Movimentacao movimentacao = this.movimentacaoService.realizarMovimentacao(m);
        URI uri = uriBuilder.path("/categoria/{uuid}").buildAndExpand(movimentacao.getIdMovimentacao()).toUri();
        return ResponseEntity.created(uri).body(movimentacao);
    }
}
