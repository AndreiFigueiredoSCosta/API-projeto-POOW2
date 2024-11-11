package com.example.demo.service;

import com.example.demo.model.Produto.Produto;
import com.example.demo.model.Produto.ProdutoRepository;
import com.example.demo.model.fornecedor.Fornecedor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public List<Fornecedor> listarFornecedoresDoProduto(UUID id){
        Produto p = this.produtoRepository.findByIdProduto(id);
        return p.getFornecedores();
    }

    public void adicionarProduto(Produto produto){
        this.produtoRepository.save(produto);
    }

    public void deletarProduto(UUID id){
        Produto p = this.produtoRepository.deleteProdutoByIdProduto(id);
    }

    public void atualizarProduto(Produto produto){
        Produto p = this.produtoRepository.findByIdProduto(produto.getIdProduto());
        p.setNomeProduto(produto.getNomeProduto());
        p.setCategoria(produto.getCategoria());
        p.setPreco(produto.getPreco());
        this.produtoRepository.save(p);
    }
}
