package com.example.demo.model.Produto;

import com.example.demo.model.fornecedor.Fornecedor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private UUID idProduto;

    @NotBlank
    @Column(name="nome_produto")
    private String nomeProduto;

    @NotBlank
    @Column(name="qtd_em_estoque")
    private int qtdEmEstoque;

    @NotBlank
    private int preco;

    @ManyToMany
    @JoinTable(
        name = "fornece",
        joinColumns = @JoinColumn(name = "id_produto"),
        inverseJoinColumns = @JoinColumn(name = "id_fornecedor")
    )

    private List<Fornecedor> fornecedores;
}
