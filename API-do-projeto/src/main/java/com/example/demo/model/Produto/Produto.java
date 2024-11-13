package com.example.demo.model.Produto;

import com.example.demo.model.categoria.Categoria;
import com.example.demo.model.fornecedor.Fornecedor;
import com.example.demo.model.movimentacao.Movimentacao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private UUID idProduto;

    @NotBlank
    @Column(name="nome_produto")
    private String nomeProduto;

    @NotNull
    @Column(name="qtd_em_estoque")
    private int qtdEmEstoque;

    @NotNull
    private int preco;

    @NotNull
    @ManyToMany
    @JoinTable(
        name = "fornece",
        joinColumns = @JoinColumn(name = "id_produto"),
        inverseJoinColumns = @JoinColumn(name = "id_fornecedor")
    )
    private List<Fornecedor> fornecedores;

    @OneToMany(mappedBy = "produto")
    private List<Movimentacao> movimentacoes;


    @JsonBackReference
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;
}
