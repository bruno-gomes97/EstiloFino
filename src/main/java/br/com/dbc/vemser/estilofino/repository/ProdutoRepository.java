package br.com.dbc.vemser.estilofino.repository;

import br.com.dbc.vemser.estilofino.dto.produtoDTO.ProdutoDTO;
import br.com.dbc.vemser.estilofino.entity.Categoria;
import br.com.dbc.vemser.estilofino.entity.Produto;
import br.com.dbc.vemser.estilofino.entity.StatusProduto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ProdutoRepository {
    private static List<Produto> listaProduto = new ArrayList<>();
    private static final AtomicInteger COUNTER = new AtomicInteger();

    public ProdutoRepository() {
        listaProduto.add(new Produto(COUNTER.incrementAndGet(),"Camiseta Feminina", Categoria.ROUPAS_FEMININAS,"Vermelha",38.0,49.90,10, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(),"Bermuda Masculina",Categoria.ROUPAS_MASCULINAS,"Azul",42.0,89.90,5,StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(),"Biquíni",Categoria.MODA_PRAIA,"Amarelo", 36.0, 79.90, 8, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Tênis Esportivo", Categoria.SAPATOS, "Preto", 40.0, 199.90, 3, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Jaqueta Masculina", Categoria.ROUPAS_MASCULINAS, "Cinza", 44.0, 299.90, 2, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Short Feminino", Categoria.ROUPAS_FEMININAS, "Amarelo", 38.0, 49.90, 14, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Jaqueta Jeans Masculina", Categoria.ROUPAS_MASCULINAS, "Azul Claro", 44.0, 189.90, 7, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Saída de Praia", Categoria.MODA_PRAIA, "Branca", 38.0, 69.90, 10, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Mocassim", Categoria.SAPATOS, "Marrom", 42.0, 149.90, 6, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Cardigan Feminino", Categoria.ROUPAS_FEMININAS, "Bege", 40.0, 79.90, 11, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Polo Masculina", Categoria.ROUPAS_MASCULINAS, "Verde", 42.0, 99.90, 12, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Chapéu de Praia", Categoria.MODA_PRAIA, "Palha", 0.0, 39.90, 20, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Bota Feminina", Categoria.SAPATOS, "Preto", 38.0, 229.90, 4, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Cropped Feminino", Categoria.ROUPAS_FEMININAS, "Vermelho", 36.0, 45.90, 13, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Cueca Boxer", Categoria.ROUPAS_MASCULINAS, "Preto", 0.0, 29.90, 18, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Biquíni Infantil", Categoria.MODA_PRAIA, "Roxo", 34.0, 59.90, 9, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Sapato Social Masculino", Categoria.SAPATOS, "Preto", 41.0, 199.90, 5, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Camisa Estampada Feminina", Categoria.ROUPAS_FEMININAS, "Colorida", 38.0, 69.90, 8, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Blusa Manga Longa Masculina", Categoria.ROUPAS_MASCULINAS, "Cinza", 46.0, 79.90, 10, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Toalha de Praia", Categoria.MODA_PRAIA, "Estampada", 0.0, 49.90, 25, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Sapatilha Feminina", Categoria.SAPATOS, "Vermelha", 36.0, 99.90, 6, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Macacão Feminino", Categoria.ROUPAS_FEMININAS, "Preto", 40.0, 129.90, 3, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Blusa Regata Masculina", Categoria.ROUPAS_MASCULINAS, "Azul", 42.0, 39.90, 15, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Chinelo de Praia", Categoria.MODA_PRAIA, "Amarelo", 39.0, 29.90, 22, StatusProduto.DISPONIVEL));
        listaProduto.add(new Produto(COUNTER.incrementAndGet(), "Tênis de Corrida", Categoria.SAPATOS, "Cinza", 43.0, 299.90, 2, StatusProduto.DISPONIVEL));
    }

    public Produto inserir(Produto produto){
        produto.setIdProduto(COUNTER.incrementAndGet());
        listaProduto.add(produto);
        return produto;
    }

    public List<Produto> listar() {
        return listaProduto;
    }

    public List<Produto> listarPorNome(String nome) {
        return listaProduto.stream()
                .filter(produto -> produto.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }

    public void delete(Produto produto) {
        listaProduto.remove(produto);
    }

}
