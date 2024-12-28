package br.com.dbc.vemser.estilofino.repository;

import br.com.dbc.vemser.estilofino.entity.Carrinho;
import br.com.dbc.vemser.estilofino.entity.ItemCarrinho;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CarrinhoRepository {
    private List<Carrinho> carrinhos = new ArrayList<>();
    private static AtomicInteger COUNTER = new AtomicInteger();

    public CarrinhoRepository() {
    }

    public Carrinho criarCarrinho(Carrinho carrinho){
        carrinho.setIdCarrinho(COUNTER.incrementAndGet());
        carrinhos.add(carrinho);
        return carrinho;
    }

    public void removerTodosItens(Carrinho carrinho){
        carrinho.getItens().clear();
    }

    public void removerItem(Carrinho carrinho, ItemCarrinho itemCarrinho){
        carrinho.getItens().remove(itemCarrinho);
    }

    public Optional<Carrinho> findByIdCliente(Integer id) {
        return carrinhos.stream()
                .filter(c -> c.getIdCliente().equals(id))
                .findFirst();
    }
}