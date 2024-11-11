package com.example.demo.model.movimentacao;

import com.example.demo.model.Produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")
    private UUID idMovimentacao;

    private int qtd;

    private Timestamp data;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private Boolean entrada;
}
