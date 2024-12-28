package br.com.dbc.vemser.estilofino.repository;


import br.com.dbc.vemser.estilofino.entity.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class PedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();

    public Pedido criarPedido(Pedido pedido){
        pedido.setIdPedido(UUID.randomUUID());
        pedidos.add(pedido);
        return pedido;
    }

    public List<Pedido> listarPedidosPorIdCliente(Integer id){
        return pedidos.stream()
                .filter(p -> p.getCliente().getIdCliente().equals(id))
                .toList();
    }
}
