package com.example.demo.model.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {
    public void deleteFornecedorByIdFornecedor(UUID id);
    public Fornecedor findFornecedorByIdFornecedor(UUID id);
}
