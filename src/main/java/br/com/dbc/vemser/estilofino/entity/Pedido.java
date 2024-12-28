package br.com.dbc.vemser.estilofino.entity;

import br.com.dbc.vemser.estilofino.dto.pedido.ClientePedidoDTO;
import br.com.dbc.vemser.estilofino.dto.pedido.EnderecoPedidoDTO;
import br.com.dbc.vemser.estilofino.dto.pedido.ProdutoPedidoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Pedido {
    private UUID idPedido;
    private LocalDate dataPedido;
    private double total;
    private List<ProdutoPedidoDTO> produtos;
    private ClientePedidoDTO cliente;
    private EnderecoPedidoDTO enderecoEntrega;

    public Pedido(LocalDate dataPedido, double total, List<ProdutoPedidoDTO> produtos, ClientePedidoDTO cliente, EnderecoPedidoDTO enderecoEntrega) {
        this.dataPedido = dataPedido;
        this.total = total;
        this.produtos = produtos;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
    }
}
