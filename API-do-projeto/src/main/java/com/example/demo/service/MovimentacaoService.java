package com.example.demo.service;

import com.example.demo.dto.MovimentacaoDto;
import com.example.demo.exceptions.ObjetoNaoEncontradoException;
import com.example.demo.exceptions.QuantidadeInvalidaException;
import com.example.demo.model.Produto.Produto;
import com.example.demo.model.Produto.ProdutoRepository;
import com.example.demo.model.movimentacao.Movimentacao;
import com.example.demo.model.movimentacao.MovimentacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovimentacaoService {

    private MovimentacaoRepository movimentacaoRepository;

    private ProdutoRepository produtoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, ProdutoRepository produtoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Movimentacao> listarMovimentacoes(){
        return this.movimentacaoRepository.findAll();
    }

    public Movimentacao listarMovimentacao(UUID id){
        Movimentacao m = this.movimentacaoRepository.findByIdMovimentacao(id);
        if(m == null){
            throw new ObjetoNaoEncontradoException("Movimentacao de id: " + id + " nao encontrada!");
        }

        return m ;
    }
    public Movimentacao realizarMovimentacao(MovimentacaoDto movimentacaoDto){
        Movimentacao m = new Movimentacao();
        m.setQtd(movimentacaoDto.getQtd());
        m.setEntrada(movimentacaoDto.isEntrada());
        Produto p =  this.produtoRepository.findByIdProduto(movimentacaoDto.getProduto());
        if(p == null){
            throw new ObjetoNaoEncontradoException("Movimentacao de id: " + movimentacaoDto.getProduto() + " nao encontrada!");
        }
        m.setProduto(p);

        if(m.getQtd() <= 0){
            throw new QuantidadeInvalidaException("Quantidade " + m.getQtd() + " é inválida");
        }

        if(movimentacaoDto.isEntrada()){
            p.setQtdEmEstoque(p.getQtdEmEstoque() + movimentacaoDto.getQtd());
        }
        else{
            if(m.getQtd() > p.getQtdEmEstoque()){
                throw new QuantidadeInvalidaException("Quantidade " + m.getQtd() + " é maior do que a disponível no estoque!");
            }

            p.setQtdEmEstoque(p.getQtdEmEstoque() - movimentacaoDto.getQtd());
        }

        this.movimentacaoRepository.save(m);
        return m;
    }

}
