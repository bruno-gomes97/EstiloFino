package br.com.dbc.vemser.estilofino.dto.carrinho;

import br.com.dbc.vemser.estilofino.entity.ItemCarrinho;
import lombok.Data;

import java.util.List;

@Data
public class CarrinhoDTO {
    private Integer idCarrinho;
    private Integer idCliente;
    private List<ItemCarrinho> itens;
    private double total;
    private int quantidadeItens;
}
