package br.com.dbc.vemser.estilofino.dto.pedido;

import lombok.Data;

@Data
public class ClientePedidoDTO {
    private Integer idCliente;
    private String nome;
    private String email;
    private String CPF;
}
