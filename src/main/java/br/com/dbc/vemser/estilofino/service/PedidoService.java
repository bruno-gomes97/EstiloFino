package br.com.dbc.vemser.estilofino.service;

import br.com.dbc.vemser.estilofino.dto.cliente.ClienteDTO;
import br.com.dbc.vemser.estilofino.dto.pedido.ClientePedidoDTO;
import br.com.dbc.vemser.estilofino.dto.pedido.EnderecoPedidoDTO;
import br.com.dbc.vemser.estilofino.dto.pedido.PedidoDTO;
import br.com.dbc.vemser.estilofino.dto.pedido.ProdutoPedidoDTO;
import br.com.dbc.vemser.estilofino.entity.*;
import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import br.com.dbc.vemser.estilofino.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ObjectMapper mapper;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PedidoDTO criarPedido(Carrinho carrinho, Cliente cliente, Endereco endereco) throws RegraDeNegocioException {
        LocalDate localDate = LocalDate.parse(LocalDate.now().format(fmt), fmt);
        ClientePedidoDTO clienteDTO = mapper.convertValue(cliente, ClientePedidoDTO.class);
        EnderecoPedidoDTO enderecoPedidoDTO = mapper.convertValue(endereco, EnderecoPedidoDTO.class);

        List<ProdutoPedidoDTO> produtos = new ArrayList<>();
        for(ItemCarrinho itemCarrinho : carrinho.getItens()){
            Produto produto = produtoService.findById(itemCarrinho.getIdProduto());
            ProdutoPedidoDTO produtoPedidoDTO = mapper.convertValue(produto, ProdutoPedidoDTO.class);
            produtoPedidoDTO.setQuantidade(itemCarrinho.getQuantidade());
            produtoPedidoDTO.setSubtotal(itemCarrinho.getSubtotal());
            produtos.add(produtoPedidoDTO);
        }

        Pedido pedido = new Pedido(localDate, carrinho.getTotal(), produtos, clienteDTO, enderecoPedidoDTO);
        return mapper.convertValue(pedidoRepository.criarPedido(pedido), PedidoDTO.class);
    }

    public List<PedidoDTO> listarPedidosPorIdCliente(Integer idCliente) throws RegraDeNegocioException {
        clienteService.findById(idCliente);

        List<Pedido> pedidos = pedidoRepository.listarPedidosPorIdCliente(idCliente);
        if(pedidos.isEmpty()){
            throw new RegraDeNegocioException("Esse cliente não possui nenhum pedido registrado no momento.");
        }

        return pedidos.stream().map(p -> mapper.convertValue(p, PedidoDTO.class)).toList();
    }
}
