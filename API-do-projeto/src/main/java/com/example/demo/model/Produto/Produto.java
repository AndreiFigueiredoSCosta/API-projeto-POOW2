package com.example.demo.model.Produto;

import com.example.demo.model.categoria.Categoria;
import com.example.demo.model.fornecedor.Fornecedor;
import com.example.demo.model.movimentacao.Movimentacao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProduto")
@Schema(description ="Entidade que representa um produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    @Schema(description = "Identificador único do produto")
    private UUID idProduto;

    @NotBlank
    @Column(name="nome_produto")
    @Schema(description = "Nome do produto", example = "berinjela")
    private String nomeProduto;

    @NotNull
    @Column(name="qtd_em_estoque")
    @Schema(description = "Quantidade disponível em etoque", example = "100")
    private int qtdEmEstoque;

    @NotNull
    @Schema(description = "Preço do produto", example ="100")
    private int preco;

    @NotNull
    @ManyToMany
    @JoinTable(
        name = "fornece",
        joinColumns = @JoinColumn(name = "id_produto"),
        inverseJoinColumns = @JoinColumn(name = "id_fornecedor")
    )
    @Schema(description = "Representa os fornecedores que fornecem esse produto")
    private List<Fornecedor> fornecedores;

    @OneToMany(mappedBy = "produto")
    @Schema(description = "Representa todas as movimentações realizadas sobre esse produto")
    private List<Movimentacao> movimentacoes;


    @JsonBackReference
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_categoria")
    @Schema(description = "Representa a categoria a qual o produto pertence", example = "Legumes")
    private Categoria categoria;
}
