package br.com.dbc.vemser.estilofino.dto.produtoDTO;

import br.com.dbc.vemser.estilofino.entity.Categoria;
import br.com.dbc.vemser.estilofino.entity.StatusProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ProdutoCreateDTO {

    @NotBlank
    @Schema(description = "Nome do produto", example = "Camisa")
    private String nome;

    @NotNull
    @Schema(description = "Categoria do produto", example = "SAPATOS")
    private Categoria categoria;

    @NotNull
    @Schema(description = "Cor do produto", example = "Azul")
    private String cor;

    @NotNull
    @Positive
    @Schema(description = "Tamanho do produto", example = "20.20")
    private double tamanho;

    @NotNull
    @Positive
    @Schema(description = "Preço do produto", example = "19.50")
    private double preco;

    @Min(value = 1)
    @Schema(description = "Quantidade do produto", example = "50")
    private int quantidade;

    @NotNull
    @Schema(description = "Status do produto", example = "DISPONIVEL")
    private StatusProduto statusProduto;
}
