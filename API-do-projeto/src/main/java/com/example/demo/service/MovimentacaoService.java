package com.example.demo.service;

import com.example.demo.model.Produto.ProdutoRepository;
import com.example.demo.model.movimentacao.Movimentacao;
import com.example.demo.model.movimentacao.MovimentacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {

    private MovimentacaoRepository movimentacaoRepository;

    private ProdutoRepository produtoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, ProdutoRepository produtoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
    }


}
