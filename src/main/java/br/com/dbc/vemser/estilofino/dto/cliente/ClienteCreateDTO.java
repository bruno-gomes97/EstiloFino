package br.com.dbc.vemser.estilofino.dto.cliente;

import br.com.dbc.vemser.estilofino.entity.Endereco;
import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteCreateDTO {

    @NotEmpty
    @Schema(description = "Email do cliente", example = "bob@gmail.com")
    private String email;

    @NotEmpty
    @Schema(description = "Nome do cliente", example = "Bob")
    private String nome;

    @NotNull
    @Size(min = 11, max = 11)
    @Schema(description = "CPF do cliente", example = "12345678901")
    private String cpf;

    @Size(min = 8, max = 11)
    @NotEmpty
    @Schema(description = "Telefone do cliente", example = "12345678901")
    private String telefone;

    @Past
    @NotNull
    @Schema(description = "Data de nascimento do cliente", example = "28/08/2002")
    private LocalDate dataNascimento;
}
