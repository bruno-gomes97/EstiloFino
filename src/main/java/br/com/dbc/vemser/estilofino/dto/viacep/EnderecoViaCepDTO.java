package br.com.dbc.vemser.estilofino.dto.viacep;

import lombok.Data;

@Data
public class EnderecoViaCepDTO {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String estado;
    private String regiao;
    private String uf;

    private Integer numero;
    private String complemento;
}
