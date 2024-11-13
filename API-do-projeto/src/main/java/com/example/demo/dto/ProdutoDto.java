package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ProdutoDto {

    @NotBlank
    private String nomeProduto;

    @NotNull
    private Integer qtdEmEstoque;

    @NotNull
    private Integer preco;

    @NotNull
    private List<UUID> fornecedores;

    @NotNull
    private UUID categoria;
}
