package br.com.dbc.vemser.estilofino.repository;

import br.com.dbc.vemser.estilofino.entity.Endereco;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EnderecoRepository {
    private List<Endereco> listaEnderecos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public Endereco create(Endereco endereco){
        endereco.setIdEndereco(COUNTER.incrementAndGet());
        listaEnderecos.add(endereco);
        return endereco;
    }

    public Endereco update(Integer id, Endereco enderecoAtualizado){
        Endereco enderecoRecuperado = listaEnderecos.stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Endereço com id " + id + " não encontrado"));

        enderecoRecuperado.setLogradouro(enderecoAtualizado.getLogradouro());
        enderecoRecuperado.setNumero(enderecoAtualizado.getNumero());
        enderecoRecuperado.setCep(enderecoAtualizado.getCep());
        enderecoRecuperado.setBairro(enderecoAtualizado.getBairro());
        enderecoRecuperado.setComplemento(enderecoAtualizado.getComplemento());
        enderecoRecuperado.setEstado(enderecoAtualizado.getEstado());

        return enderecoAtualizado;
    }


    public void delete (Integer id){
        listaEnderecos.remove(id);
    }

    public List<Endereco> list(){
        return listaEnderecos;
    }


    public List<Endereco> lisByIdPessoa(Integer id){
        return listaEnderecos.stream()
                .filter(endereco -> endereco.getIdCliente().equals(id))
                .collect(Collectors.toList());
    }

}
