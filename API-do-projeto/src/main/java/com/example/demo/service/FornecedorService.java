package com.example.demo.service;

import com.example.demo.model.Produto.Produto;
import com.example.demo.model.fornecedor.Fornecedor;
import com.example.demo.model.fornecedor.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FornecedorService {
    private FornecedorRepository repository;
    public FornecedorService(FornecedorRepository repository){
        this.repository=repository;
    }

    public List<Fornecedor> ListarFornecedores(){
        return this.repository.findAll();
    }

    public List<Produto> listarProdutosDoFornecedor(UUID idFornecedor){
        Fornecedor f = this.repository.findFornecedorByIdFornecedor(idFornecedor);
        return f.getProdutos();
    }

    public void salvar(Fornecedor fornecedor){
        this.repository.save(fornecedor);
    }

    public void deletar(UUID idFornecedor){
        this.repository.deleteFornecedorByIdFornecedor(idFornecedor);
    }

    public void atualizar(Fornecedor fornecedor){
        Fornecedor f = this.repository.findFornecedorByIdFornecedor(fornecedor.getIdFornecedor());
        f.setNomeFornecedor(fornecedor.getNomeFornecedor());
        f.setCnpj(fornecedor.getCnpj());
        f.setTelefone(fornecedor.getTelefone());
        this.repository.save(f);
    }

}
