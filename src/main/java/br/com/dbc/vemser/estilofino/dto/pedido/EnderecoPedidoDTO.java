package br.com.dbc.vemser.estilofino.dto.pedido;

import lombok.Data;

@Data
public class EnderecoPedidoDTO {
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String cep;
}
