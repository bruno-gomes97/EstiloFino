package br.com.dbc.vemser.estilofino.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrinho {
    private Integer idItem;
    private Integer idProduto;
    private int quantidade;
    private double preco;
    private double subtotal;
}
