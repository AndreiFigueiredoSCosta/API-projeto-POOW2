package com.example.demo.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    public Produto findByIdProduto(UUID idProduto);
    public void deleteProdutoByIdProduto(UUID idProduto);
}
