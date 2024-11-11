package com.example.demo.model.fornecedor;

import com.example.demo.model.Produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private UUID idFornecedor;

    @NotBlank
    @Column(name="nome_fornecedor")
    private String nomeFornecedor;

    @NotBlank
    private String cnpj;

    @NotBlank
    private String telefone;

    @ManyToMany
    @JoinTable(
            name = "fornece",
            joinColumns = @JoinColumn(name = "id_fornecedor"),
            inverseJoinColumns = @JoinColumn(name="id_produto")
    )
    private List<Produto> produtos;
}
