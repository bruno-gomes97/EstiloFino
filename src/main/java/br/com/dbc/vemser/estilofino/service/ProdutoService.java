package br.com.dbc.vemser.estilofino.service;

import br.com.dbc.vemser.estilofino.dto.produtoDTO.ProdutoCreateDTO;
import br.com.dbc.vemser.estilofino.dto.produtoDTO.ProdutoDTO;
import br.com.dbc.vemser.estilofino.entity.Categoria;
import br.com.dbc.vemser.estilofino.entity.Produto;
import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import br.com.dbc.vemser.estilofino.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

    // inserir produto
    public ProdutoDTO inserir(ProdutoCreateDTO dto) {
        Produto produto = objectMapper.convertValue(dto, Produto.class);
        return objectMapper.convertValue(produtoRepository.inserir(produto), ProdutoDTO.class);
    }

    // editar produto
    public ProdutoDTO editar(Integer id, ProdutoCreateDTO produtoAtualizar) throws RegraDeNegocioException {
        Produto produtoRecuperado = buscarProduto(id);

        produtoRecuperado.setNome(produtoAtualizar.getNome());
        produtoRecuperado.setCor(produtoAtualizar.getCor());
        produtoRecuperado.setTamanho(produtoAtualizar.getTamanho());
        produtoRecuperado.setPreco(produtoAtualizar.getPreco());
        produtoRecuperado.setQuantidade(produtoAtualizar.getQuantidade());

        return objectMapper.convertValue(produtoRecuperado, ProdutoDTO.class);
    }

    // listar todos os produtos
    public List<ProdutoDTO> listarTodos(Categoria categoria, String cor, Double tamanho,
                                        Double minPreco, Double maxPreco) throws RegraDeNegocioException {
        if(minPreco != null && maxPreco != null && maxPreco < minPreco){
            throw new RegraDeNegocioException("O preço máximo não pode ser inferior ao preço minimo");
        }
        return produtoRepository.listar().stream()
                .filter(p -> categoria == null || p.getCategoria() == categoria)
                .filter(p -> cor == null || p.getCor().equals(cor))
                .filter(p -> tamanho == null || p.getTamanho() == tamanho)
                .filter(p -> minPreco == null || p.getPreco() >= minPreco)
                .filter(p -> maxPreco == null || p.getPreco() <= maxPreco)
                .map(p -> objectMapper.convertValue(p, ProdutoDTO.class))
                .toList();
    }

    // listar produtos com filtro
//    public List<ProdutoDTO> listar(String nome) {
//        List<Produto> produtos = produtoRepository.listarPorNome(nome);
//        return produtos.stream()
//                .map(produto -> objectMapper.convertValue(produto, ProdutoDTO.class))
//                .collect(Collectors.toList());
//    }

    // remover
    public void remover(Integer idProduto) throws RegraDeNegocioException {
        Produto produtoRecuperado = buscarProduto(idProduto);
        produtoRepository.delete(produtoRecuperado);
    }

    // buscar produto por ID
    private Produto buscarProduto(Integer id) throws RegraDeNegocioException {
        return produtoRepository.listar().stream()
                .filter(produto -> produto.getIdProduto().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado!"));
    }

    public Produto findById(Integer id) throws RegraDeNegocioException {
        return buscarProduto(id);
    }
}
