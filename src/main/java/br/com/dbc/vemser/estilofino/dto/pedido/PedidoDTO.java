package br.com.dbc.vemser.estilofino.dto.pedido;

import br.com.dbc.vemser.estilofino.entity.ItemCarrinho;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class PedidoDTO {
    private UUID idPedido;
    private LocalDate dataPedido;
    private double total;
    private List<ProdutoPedidoDTO> produtos;
    private ClientePedidoDTO cliente;
    private EnderecoPedidoDTO enderecoEntrega;
}
