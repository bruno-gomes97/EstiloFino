package br.com.dbc.vemser.estilofino.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Integer idCliente;
    private String email;
    private String nome;
    private String CPF;
    private String telefone;
    private LocalDate dataNascimento;
}
