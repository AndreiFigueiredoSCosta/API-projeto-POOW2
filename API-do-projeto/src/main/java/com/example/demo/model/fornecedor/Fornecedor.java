package com.example.demo.model.fornecedor;

import com.example.demo.model.Produto.Produto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Identificador unico do fornecedor")
    private UUID idFornecedor;

    @NotBlank
    @Column(name="nome_fornecedor")
    @Schema(description = "Nome do fornecedor", example = "Marcio show de bola lmtd.")
    private String nomeFornecedor;

    @NotBlank
    @Schema(description = "CNPJ do fornecedor", example = "12.345.678/0001-95")
    private String cnpj;

    @NotBlank
    @Schema(description = "Telefone do fornecedor", example = "(11) 98765-4321")
    private String telefone;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "fornece",
            joinColumns = @JoinColumn(name = "id_fornecedor"),
            inverseJoinColumns = @JoinColumn(name="id_produto")
    )
    @Schema(description = "Representa os produtos que esse fornecedor fornece")
    private List<Produto> produtos;
}
