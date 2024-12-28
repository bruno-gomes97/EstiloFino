package br.com.dbc.vemser.estilofino.service;

import br.com.dbc.vemser.estilofino.dto.carrinho.AdicionarItemDTO;
import br.com.dbc.vemser.estilofino.dto.carrinho.AtualizarItemDTO;
import br.com.dbc.vemser.estilofino.dto.carrinho.CarrinhoDTO;
import br.com.dbc.vemser.estilofino.dto.pedido.PedidoDTO;
import br.com.dbc.vemser.estilofino.entity.*;
import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import br.com.dbc.vemser.estilofino.repository.CarrinhoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoService produtoService;
    private final ObjectMapper mapper;
    private final EmailService emailService;
    private final ClienteService clienteService;
    private final EnderecoService enderecoService;
    private final PedidoService pedidoService;

    public CarrinhoDTO adicionarItem(AdicionarItemDTO adicionarItemDTO) throws RegraDeNegocioException {
        clienteService.findById(adicionarItemDTO.getIdCliente());
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findByIdCliente(adicionarItemDTO.getIdCliente());
        Carrinho carrinho = carrinhoOptional.orElseGet(() -> new Carrinho(adicionarItemDTO.getIdCliente()));
        Produto produto = produtoService.findById(adicionarItemDTO.getIdProduto());

        validarQuantidadeEmEstoque(produto, adicionarItemDTO.getQuantidade(), 0);

        Optional<ItemCarrinho> itemCarrinhoOptional = getItemCarrinhoIdProduto(carrinho, adicionarItemDTO.getIdProduto());
        ItemCarrinho itemCarrinho = itemCarrinhoOptional.orElseGet(() -> new ItemCarrinho(carrinho.getItens().size() + 1,
                adicionarItemDTO.getIdProduto(), adicionarItemDTO.getQuantidade(),
                produto.getPreco(), adicionarItemDTO.getQuantidade() * produto.getPreco()));

        if (itemCarrinhoOptional.isPresent()) {
            itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + adicionarItemDTO.getQuantidade());
            itemCarrinho.setSubtotal(itemCarrinho.getPreco() * itemCarrinho.getQuantidade());
        } else {
            carrinho.getItens().add(itemCarrinho);
        }

        atualizarInfoCarrinho(carrinho);
        if (carrinhoOptional.isPresent()) {
            return mapper.convertValue(carrinho, CarrinhoDTO.class);
        }
        return mapper.convertValue(carrinhoRepository.criarCarrinho(carrinho), CarrinhoDTO.class);
    }

    public CarrinhoDTO visualizarCarrinho(Integer idCliente) throws RegraDeNegocioException {
        clienteService.findById(idCliente);
        return mapper.convertValue(findCarrinhoByIdCliente(idCliente), CarrinhoDTO.class);
    }

    public void removerTodosItens(Integer id) throws RegraDeNegocioException {
        clienteService.findById(id);
        Carrinho carrinho = findCarrinhoByIdCliente(id);

        for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
            Produto produto = produtoService.findById(itemCarrinho.getIdProduto());
            produto.setQuantidade(produto.getQuantidade() + itemCarrinho.getQuantidade());
            produto.setStatusProduto(StatusProduto.DISPONIVEL);
        }

        carrinhoRepository.removerTodosItens(carrinho);
        atualizarInfoCarrinho(carrinho);
    }

    public void removerItem(Integer idCliente, Integer idItem) throws RegraDeNegocioException {
        clienteService.findById(idCliente);
        Carrinho carrinho = findCarrinhoByIdCliente(idCliente);

        ItemCarrinho itemCarrinho = getItemCarrinhoIdItem(carrinho, idItem);
        Produto produto = produtoService.findById(itemCarrinho.getIdProduto());
        produto.setQuantidade(produto.getQuantidade() + itemCarrinho.getQuantidade());
        produto.setStatusProduto(StatusProduto.DISPONIVEL);

        carrinhoRepository.removerItem(carrinho, itemCarrinho);
        atualizarInfoCarrinho(carrinho);
        atualizarIdsItemCarrinho(carrinho);
    }

    public CarrinhoDTO editarItem(AtualizarItemDTO atualizarItemDTO, Integer idCliente, Integer idItem) throws RegraDeNegocioException {
        clienteService.findById(idCliente);
        Carrinho carrinho = findCarrinhoByIdCliente(idCliente);

        ItemCarrinho itemCarrinho = getItemCarrinhoIdItem(carrinho, idItem);

        Produto produto = produtoService.findById(itemCarrinho.getIdProduto());
        if (atualizarItemDTO.getQuantidade() > itemCarrinho.getQuantidade()) {
            validarQuantidadeEmEstoque(produto, atualizarItemDTO.getQuantidade(), itemCarrinho.getQuantidade());
        } else {
            if (produto.getStatusProduto() == StatusProduto.INDISPONIVEL) {
                produto.setStatusProduto(StatusProduto.DISPONIVEL);
            }
            produto.setQuantidade(produto.getQuantidade() +
                    (itemCarrinho.getQuantidade() - atualizarItemDTO.getQuantidade()));
        }

        itemCarrinho.setQuantidade(atualizarItemDTO.getQuantidade());
        itemCarrinho.setSubtotal(itemCarrinho.getPreco() * itemCarrinho.getQuantidade());
        atualizarInfoCarrinho(carrinho);
        return mapper.convertValue(carrinho, CarrinhoDTO.class);
    }

    public PedidoDTO pagarCarrinho(Integer idCliente, Integer idEndereco) throws RegraDeNegocioException {
        Cliente cliente = clienteService.findById(idCliente);
        Carrinho carrinho = findCarrinhoByIdCliente(idCliente);


        enderecoService.findByIdPessoa(idCliente);
        Endereco endereco = enderecoService.findByIdEndereco(idEndereco);
        if(!endereco.getIdCliente().equals(idCliente)){
            throw new RegraDeNegocioException("O endereço especificado não pertence ao cliente.");
        }

        List<ItemCarrinho> itens = carrinho.getItens();
        if (itens.isEmpty()) {
            throw new RegraDeNegocioException("O seu carrinho de compras está vazio!");
        }
        emailService.sendEmail(TipoEmail.PAGAMENTO, clienteService.findById(idCliente), carrinho, endereco);
        PedidoDTO pedidoDTO = pedidoService.criarPedido(carrinho, cliente, endereco);

        carrinho.getItens().clear();
        atualizarInfoCarrinho(carrinho);
        return pedidoDTO;
    }

    private Carrinho findCarrinhoByIdCliente(Integer id) throws RegraDeNegocioException {
        return carrinhoRepository.findByIdCliente(id)
                .orElseThrow(() -> new RegraDeNegocioException("Esse cliente não possui carrinho de compras registrado no momento."));
    }

    private ItemCarrinho getItemCarrinhoIdItem(Carrinho carrinho, Integer idItem) throws RegraDeNegocioException {
        return carrinho.getItens().stream().filter(i -> i.getIdItem().equals(idItem))
                .findFirst().orElseThrow(() -> new RegraDeNegocioException("Item com id especificado não está no carrinho."));
    }

    private Optional<ItemCarrinho> getItemCarrinhoIdProduto(Carrinho carrinho, Integer idProduto) {
        return carrinho.getItens().stream().filter(i -> i.getIdProduto().equals(idProduto)).findFirst();
    }

    private void atualizarInfoCarrinho(Carrinho carrinho) {
        if (carrinho.getItens().isEmpty()) {
            carrinho.setTotal(0);
            carrinho.setQuantidadeItens(0);
            return;
        }

        carrinho.setTotal(carrinho.getItens().stream().mapToDouble(i -> i.getSubtotal()).sum());
        carrinho.setQuantidadeItens(carrinho.getItens().stream().mapToInt(i -> i.getQuantidade()).sum());
    }

    private void atualizarIdsItemCarrinho(Carrinho carrinho) {
        List<ItemCarrinho> itens = carrinho.getItens();
        for (int i = 0; i < itens.size(); i++) {
            itens.get(i).setIdItem(i + 1);
        }
    }

    private void validarQuantidadeEmEstoque(Produto produto, int quantidade, int quantidadeAnterior) throws RegraDeNegocioException {
        if ((produto.getQuantidade() + quantidadeAnterior) < quantidade || produto.getStatusProduto() == StatusProduto.INDISPONIVEL) {
            throw new RegraDeNegocioException("Quantidade maior do que possui em estoque!");
        }
        produto.setQuantidade((produto.getQuantidade() + quantidadeAnterior) - quantidade);
        if (produto.getQuantidade() == 0) produto.setStatusProduto(StatusProduto.INDISPONIVEL);
    }
}