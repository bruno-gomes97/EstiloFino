package br.com.dbc.vemser.estilofino.dto.carrinho;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdicionarItemDTO {
    @NotNull(message = "ID do produto não pode ser nulo.")
    @Schema(description = "ID do item", example = "1")
    private Integer idProduto;
    @NotNull(message = "ID do cliente não pode ser nulo.")
    @Schema(description = "ID do cliente", example = "1")
    private Integer idCliente;
    @NotNull(message = "Quantidade não pode ser nula.")
    @Min(value = 1, message = "Quantidade mínima de produtos é 1")
    @Schema(description = "Quantidade de itens", example = "20")
    private int quantidade;
}
