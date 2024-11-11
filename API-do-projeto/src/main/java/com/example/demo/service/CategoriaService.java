package com.example.demo.service;

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
        this.repository.deleteByIdCategoria(id);
    }

    public List<Categoria> listar(){
        return this.repository.findAll();
    }

    public void atualizar(Categoria categoria){
        Categoria c = this.repository.findCategoriaByIdCategoria(categoria.getIdCategoria());
        c.setNomeCategoria(categoria.getNomeCategoria());
        this.repository.save(categoria);
    }
}
