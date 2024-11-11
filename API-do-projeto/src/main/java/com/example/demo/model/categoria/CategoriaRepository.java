package com.example.demo.model.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
    public void deleteByIdCategoria(UUID id);
    public Categoria findCategoriaByIdCategoria(UUID id);
}
