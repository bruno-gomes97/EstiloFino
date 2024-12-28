package br.com.dbc.vemser.estilofino.service;

import br.com.dbc.vemser.estilofino.dto.cliente.ClienteCreateDTO;
import br.com.dbc.vemser.estilofino.dto.cliente.ClienteDTO;
import br.com.dbc.vemser.estilofino.entity.Cliente;
import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import br.com.dbc.vemser.estilofino.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ObjectMapper mapper;
    private final ClienteRepository clienteRepository;
    private final EmailService emailService;

    public List<ClienteDTO> listarClientes() {
       return repository.listarClientes().stream().map(c -> mapper.convertValue(c, ClienteDTO.class)).toList();
    }

    public ClienteDTO listarClienteEspecifico(Integer id) {
        return mapper.convertValue(repository.listarClienteEspecifico(id), ClienteDTO.class);
    }

    public ClienteDTO criarCliente(ClienteCreateDTO dto) {
        Cliente cliente = mapper.convertValue(dto, Cliente.class);
        Cliente clienteCriado = repository.criarCliente(cliente);
        emailService.sendEmail(TipoEmail.CRIACAO, clienteCriado, null, null);
        return mapper.convertValue(clienteCriado, ClienteDTO.class);
    }

    public ClienteDTO editarCliente(ClienteCreateDTO dto, Integer id) throws RegraDeNegocioException {
        Cliente clienteDesatualizado = findById(id);
        return mapper.convertValue(clienteRepository.editarCliente(clienteDesatualizado, dto), ClienteDTO.class);
    }

    public void removerCliente(Integer id) {
        repository.delete(id);
    }

    public Cliente findById(Integer idCliente) throws RegraDeNegocioException {
        return clienteRepository.listarClientes()
                .stream().filter(cliente -> cliente.getIdCliente().equals(idCliente))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Esse cliente não existe!"));
    }

}
