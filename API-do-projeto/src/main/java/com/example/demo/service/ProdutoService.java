package com.example.demo.service;

import com.example.demo.dto.ProdutoDto;
import com.example.demo.exceptions.ObjetoNaoEncontradoException;
import com.example.demo.model.Produto.Produto;
import com.example.demo.model.Produto.ProdutoRepository;
import com.example.demo.model.categoria.Categoria;
import com.example.demo.model.categoria.CategoriaRepository;
import com.example.demo.model.fornecedor.Fornecedor;
import com.example.demo.model.fornecedor.FornecedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;
    private FornecedorRepository fornecedorRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, FornecedorRepository fornecedorRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Produto listarProduto(UUID id){
        Produto p = this.produtoRepository.findByIdProduto(id);
        if(p == null){
            throw new ObjetoNaoEncontradoException("Produto de id: " + id + " nao encontrado!");
        }

        return p;
    }

    public List<Fornecedor> listarFornecedoresDoProduto(UUID id){
        Produto p = this.produtoRepository.findByIdProduto(id);
        if(p == null){
            throw new ObjetoNaoEncontradoException("Produto de id: " + id + " nao encontrado!");
        }

        return p.getFornecedores();
    }

    @Transactional
    public Produto adicionarProduto(ProdutoDto pdto){
        Produto produto = new Produto();
        produto.setNomeProduto(pdto.getNomeProduto());
        produto.setQtdEmEstoque(pdto.getQtdEmEstoque());
        produto.setPreco(pdto.getPreco());
        Categoria c = this.categoriaRepository.findCategoriaByIdCategoria(pdto.getCategoria());
        if(c == null){
            throw new ObjetoNaoEncontradoException("Categoria de id: " + pdto.getCategoria() + " nao encontrado!");
        }

        produto.setCategoria(c);
        List<Fornecedor> fornecedores = new ArrayList<>();
        produto.setFornecedores(fornecedores);
        produto = this.produtoRepository.save(produto);

        for(UUID id : pdto.getFornecedores()){
            Fornecedor f = this.fornecedorRepository.findFornecedorByIdFornecedor(id);
            if(f == null){
                throw new ObjetoNaoEncontradoException("Fornecedor de id: " + id + " nao encontrado!");
            }

            fornecedores.add(f);
            f.getProdutos().add(produto);
            this.fornecedorRepository.save(f);
        }

        c.getProdutos().add(produto);
        this.categoriaRepository.save(c);
        produto.setFornecedores(fornecedores);
        this.produtoRepository.save(produto);
        return produto;
    }

    public void deletarProduto(UUID id){
        if(id == null){
            throw new ObjetoNaoEncontradoException("Produto de id: " + id + " nao encontrado!");
        }

        this.produtoRepository.deleteProdutoByIdProduto(id);
    }

    public void atualizarProduto(Produto produto){
        Produto p = this.produtoRepository.findByIdProduto(produto.getIdProduto());
        if(p == null){
            throw new ObjetoNaoEncontradoException("Produto de id: " + produto.getIdProduto() + " nao encontrado!");
        }
        p.setNomeProduto(produto.getNomeProduto());
        p.setCategoria(produto.getCategoria());
        p.setPreco(produto.getPreco());
        this.produtoRepository.save(p);
    }
}
