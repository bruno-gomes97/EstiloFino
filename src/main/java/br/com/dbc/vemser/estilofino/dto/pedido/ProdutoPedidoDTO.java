package br.com.dbc.vemser.estilofino.dto.pedido;

import br.com.dbc.vemser.estilofino.entity.Categoria;
import lombok.Data;

@Data
public class ProdutoPedidoDTO {
    private Integer idProduto;
    private String nome;
    private Categoria categoria;
    private String cor;
    private double tamanho;
    private double preco;

    private int quantidade;
    private double subtotal;
}
