package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class MovimentacaoDto {

    @NotNull
    private Integer qtd;

    @NotNull
    private UUID produto;

    @NotNull
    private boolean entrada;
}
