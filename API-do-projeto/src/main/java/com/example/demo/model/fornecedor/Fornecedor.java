package com.example.demo.model.fornecedor;

import com.example.demo.model.Produto.Produto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idFornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private UUID idFornecedor;

    @NotBlank
    @Column(name="nome_fornecedor")
    private String nomeFornecedor;

    @NotBlank
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}",
            message = "CNPJ inválido, deve seguir o formato XX.XXX.XXX/XXXX-XX"
    )
    private String cnpj;

    @NotBlank
    @Pattern(
            regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
            message = "Telefone inválido, deve seguir o formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX"
    )
    private String telefone;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "fornece",
            joinColumns = @JoinColumn(name = "id_fornecedor"),
            inverseJoinColumns = @JoinColumn(name="id_produto")
    )
    private List<Produto> produtos;
}
