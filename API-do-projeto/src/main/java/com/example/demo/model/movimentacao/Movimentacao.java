package com.example.demo.model.movimentacao;

import com.example.demo.model.Produto.Produto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidade que representa uma movimentação")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")
    @Schema(description = "identificador da movimentação")
    private UUID idMovimentacao;

    @NotNull
    @Schema(description = "Quantidade retirada/adicionada ao estoque", example = "100")
    private int qtd;

    @Schema(description = "data e hora que a movimentação foi realizada")
    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_produto")
    @Schema(description = "Produto sobre o qual foi realizada a movimentação", example = "berinjela")
    private Produto produto;

    @NotNull
    @Schema(description = "Indica se a operação vai ser de entrada de produtos" +
            "(caso seja true) ou retirada(caso seja false)")
    private Boolean entrada;
}
