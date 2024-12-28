package br.com.dbc.vemser.estilofino.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrinho {
    private Integer idCarrinho;
    private Integer idCliente;
    private List<ItemCarrinho> itens;
    private double total;
    private int quantidadeItens;

    public Carrinho(Integer idCliente) {
        this.idCliente = idCliente;
        this.itens = new ArrayList<>();
        this.total = 0;
        this.quantidadeItens = 0;
    }
}
