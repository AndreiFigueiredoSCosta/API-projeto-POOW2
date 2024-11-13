package com.example.demo.service;

import com.example.demo.exceptions.ObjetoNaoEncontradoException;
import com.example.demo.model.Produto.Produto;
import com.example.demo.model.categoria.Categoria;
import com.example.demo.model.categoria.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {
    private CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository){
        this.repository = repository;
    }

    public void salvar(Categoria categoria){
        this.repository.save(categoria);
    }

    public void deletar(UUID id){
        Categoria c = this.repository.findCategoriaByIdCategoria(id);
        if(c == null){
            throw new ObjetoNaoEncontradoException("Categoria de id: " + id + " nao encontrada!");
        }

        this.repository.deleteByIdCategoria(id);
    }

    public List<Categoria> listar(){
        return this.repository.findAll();
    }

    public Categoria listarCategoria(UUID id){
        Categoria c = this.repository.findCategoriaByIdCategoria(id);
        if(c == null){
            throw new ObjetoNaoEncontradoException("Categoria de id: " + id + " nao encontrada!");
        }

        return this.repository.findCategoriaByIdCategoria(id);
    }

    public List<Produto> listarProdutosDaCategoria(UUID id){
        Categoria c = this.repository.findCategoriaByIdCategoria(id);
        if(c == null){
            throw new ObjetoNaoEncontradoException("Categoria de id: " + id + " nao encontrada!");
        }

        return c.getProdutos();
    }

    public void atualizar(Categoria categoria){
        Categoria c = this.repository.findCategoriaByIdCategoria(categoria.getIdCategoria());
        if(c == null){
            throw new ObjetoNaoEncontradoException("Categoria de id: " + categoria.getIdCategoria() + " nao encontrada!");
        }

        c.setNomeCategoria(categoria.getNomeCategoria());
        this.repository.save(categoria);
    }
}
