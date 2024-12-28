package br.com.dbc.vemser.estilofino.dto.enderecoDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EnderecoCreateDTO {
    @NotNull
    @Schema(description = "Cep do endereço", example = "12345678")
    private String cep;
    @NotNull
    @Schema(description = "Número do endereço", example = "10")
    private Integer numero;
    @NotEmpty @Size(max=250)
    @Schema(description = "Complemento do endereço", example = "Prédio azul")
    private String complemento;
}
