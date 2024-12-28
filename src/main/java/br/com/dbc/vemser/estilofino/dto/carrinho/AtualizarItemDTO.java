package br.com.dbc.vemser.estilofino.dto.carrinho;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AtualizarItemDTO {
    @NotNull(message = "Quantidade não pode ser nula.")
    @Min(value = 1, message = "Quantidade mínima de produtos é 1")
    @Schema(description = "Quantidade do item a ser atualizado", example = "3")
    private int quantidade;
}
