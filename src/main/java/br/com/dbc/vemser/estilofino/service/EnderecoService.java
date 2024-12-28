package br.com.dbc.vemser.estilofino.service;

import br.com.dbc.vemser.estilofino.client.ApiViaCepClient;
import br.com.dbc.vemser.estilofino.dto.enderecoDTO.EnderecoDTO;
import br.com.dbc.vemser.estilofino.dto.viacep.EnderecoViaCepDTO;
import br.com.dbc.vemser.estilofino.entity.Endereco;
import br.com.dbc.vemser.estilofino.dto.enderecoDTO.EnderecoCreateDTO;
import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import br.com.dbc.vemser.estilofino.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteService clienteService;
    private final ObjectMapper objectMapper;
    private final ApiViaCepClient viaCepClient;

    public EnderecoDTO create(Integer idCliente, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        clienteService.findById(idCliente);
        EnderecoViaCepDTO enderecoViaCepDTO = viaCepClient.buscarCep(enderecoCreateDTO.getCep());
        if(enderecoViaCepDTO == null){
            throw new RegraDeNegocioException("Cep inválido ou inexistente.");
        }
        enderecoViaCepDTO.setNumero(enderecoCreateDTO.getNumero());
        enderecoViaCepDTO.setComplemento(enderecoCreateDTO.getComplemento());

        Endereco enderecoEntity = objectMapper.convertValue(enderecoViaCepDTO, Endereco.class);
        enderecoEntity.setCidade(enderecoViaCepDTO.getLocalidade());
        enderecoEntity.setIdCliente(idCliente);
        return objectMapper.convertValue(enderecoRepository.create(enderecoEntity), EnderecoDTO.class);
    }

    public EnderecoDTO update(Integer id, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        Endereco enderecoRecuperado = getEndereco(id);
        EnderecoViaCepDTO enderecoViaCepDTO = viaCepClient.buscarCep(enderecoCreateDTO.getCep());
        if(enderecoViaCepDTO == null){
            throw new RegraDeNegocioException("Cep inválido ou inexistente.");
        }
        enderecoViaCepDTO.setNumero(enderecoCreateDTO.getNumero());
        enderecoViaCepDTO.setComplemento(enderecoCreateDTO.getComplemento());

        Endereco enderecoEntity = objectMapper.convertValue(enderecoViaCepDTO, Endereco.class);
        enderecoEntity.setIdEndereco(id);
        enderecoEntity.setIdCliente(enderecoRecuperado.getIdCliente());
        enderecoEntity.setCidade(enderecoViaCepDTO.getLocalidade());

        enderecoEntity = enderecoRepository.update(id, enderecoEntity);

        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        getEndereco(id);
        enderecoRepository.delete(id);
    }

    public List<EnderecoDTO> list() {
        List<Endereco> enderecoEntity = enderecoRepository.list();
        return enderecoEntity.stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .toList();
    }

    public List<EnderecoDTO> listByIdPessoa(Integer id) throws RegraDeNegocioException {
        clienteService.findById(id);
        List<Endereco> enderecoList = enderecoRepository.lisByIdPessoa(id);
        if(enderecoList.isEmpty()){
            throw new RegraDeNegocioException("Esse cliente não possui nenhum endereço registrado");
        }
        return enderecoList.stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .toList();
    }

    public void findByIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        List<Endereco> enderecos = enderecoRepository.lisByIdPessoa(idPessoa);
        if(enderecos.isEmpty()){
            throw new RegraDeNegocioException("Esse cliente não possui nenhum endereço registrado");
        }
    }
    public Endereco findByIdEndereco(Integer idEndereco) throws RegraDeNegocioException {
        return enderecoRepository.list().stream().filter(e -> e.getIdEndereco().equals(idEndereco))
                .findFirst().orElseThrow(() -> new RegraDeNegocioException("Endereço com o id especificado não existe"));
    }

    /*
    public boolean validacoesEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("O endereço não pode ser nulo!");
        }

        if (StringUtils.isBlank(endereco.getLogradouro()) || endereco.getLogradouro().length() > 250) {
            throw new IllegalArgumentException("Logradouro não pode ser vazio e deve conter no máximo 250 caracteres!");
        }

        if (endereco.getNumero() == null) {
            throw new IllegalArgumentException("O número não pode ser nulo!");
        }

        if (StringUtils.isBlank(endereco.getCep()) || endereco.getCep().length() > 8) {
            throw new IllegalArgumentException("O Cep não pode ser vazio nem nulo e deve conter no máximo 8 caracteres!");
        }

        if (StringUtils.isBlank(endereco.getCidade()) || endereco.getCidade().length() > 250) {
            throw new IllegalArgumentException("A Cidade não pode ser vazia nem nula e deve conter no máximo 250 caracteres!");
        }

        if (StringUtils.isBlank(endereco.getComplemento()) || endereco.getComplemento().length() > 250) {
            throw new IllegalArgumentException("O complemento não pode ser vazio nem nulo e deve conter no máximo 250 caracteres!");
        }

        if (endereco.getEstado() == null) {
            throw new IllegalArgumentException("O estado não pode ser nulo!");
        }

        return true;
    }
     */

    private Endereco getEndereco(Integer id) throws RegraDeNegocioException {
        return enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
    }
}
