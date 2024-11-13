package com.example.demo.model.movimentacao;

import com.example.demo.model.Produto.Produto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movimentacao")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idMovimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")
    private UUID idMovimentacao;

    private int qtd;

    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private Boolean entrada;
}
