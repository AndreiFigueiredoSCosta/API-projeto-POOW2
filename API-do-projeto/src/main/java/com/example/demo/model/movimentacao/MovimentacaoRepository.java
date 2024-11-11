package com.example.demo.model.movimentacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, UUID> {
    public Movimentacao findByIdMovimentacao(UUID idMovimentacao);
    public void deleteByIdMovimentacao(UUID idMovimentacao);
}
