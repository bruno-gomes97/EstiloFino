package br.com.dbc.vemser.estilofino.client;

import br.com.dbc.vemser.estilofino.dto.viacep.EnderecoViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "apiViaCepClient", url = "https://viacep.com.br/ws")
public interface ApiViaCepClient {

    @GetMapping("/{cep}/json/")
    EnderecoViaCepDTO buscarCep(@PathVariable("cep") String cep);
}
