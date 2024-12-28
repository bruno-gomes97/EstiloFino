package br.com.dbc.vemser.estilofino.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private Integer idProduto;

    @NotNull
    private String nome;

    @NotNull
    private Categoria categoria;

    @NotNull
    private String cor;

    @Positive
    private double tamanho;

    @Positive
    private double preco;

    @Min(value = 0)
    private int quantidade;

    @NotNull
    private StatusProduto statusProduto;
}
