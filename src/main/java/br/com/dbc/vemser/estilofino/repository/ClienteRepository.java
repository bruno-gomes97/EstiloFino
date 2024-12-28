package br.com.dbc.vemser.estilofino.repository;

import br.com.dbc.vemser.estilofino.dto.cliente.ClienteCreateDTO;
import br.com.dbc.vemser.estilofino.entity.Cliente;
import br.com.dbc.vemser.estilofino.entity.Endereco;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ClienteRepository {

    private static List<Cliente> listaClientes = new ArrayList<>();
    private static AtomicInteger COUNTER = new AtomicInteger();

    public ClienteRepository() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        listaClientes.add(new Cliente(COUNTER.incrementAndGet(), "mateuslima@gmail.com", "Mateus", "12345678901", "83998691247", LocalDate.parse("28/08/2002", fmt)));
        listaClientes.add(new Cliente(COUNTER.incrementAndGet(), "mateuslima@gmail.com", "Mateus", "12345678901", "83998691247", LocalDate.parse("28/08/2002", fmt)));
        listaClientes.add(new Cliente(COUNTER.incrementAndGet(), "mateuslima@gmail.com", "Mateus", "12345678901", "83998691247", LocalDate.parse("28/08/2002", fmt)));
        listaClientes.add(new Cliente(COUNTER.incrementAndGet(), "mateuslima@gmail.com", "Mateus", "12345678901", "83998691247", LocalDate.parse("28/08/2002", fmt)));
        listaClientes.add(new Cliente(COUNTER.incrementAndGet(), "mateuslima@gmail.com", "Mateus", "12345678901", "83998691247", LocalDate.parse("28/08/2002", fmt)));
    }

    public List<Cliente> listarClientes() {
        return listaClientes;
    }

    public Cliente listarClienteEspecifico(Integer id) {
        return listaClientes.stream()
                .filter(cliente -> cliente.getIdCliente().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cliente com ID " + id + " não encontrado"));
    }

    public Cliente criarCliente(Cliente cliente) {
        cliente.setIdCliente(COUNTER.incrementAndGet());
        listaClientes.add(cliente);
        return cliente;
    }

    public Cliente editarCliente(Cliente clienteDesatualizado, ClienteCreateDTO novoCliente) {
        clienteDesatualizado.setEmail(novoCliente.getEmail());
        clienteDesatualizado.setNome(novoCliente.getNome());
        clienteDesatualizado.setCPF(novoCliente.getCpf());
        clienteDesatualizado.setTelefone(novoCliente.getTelefone());
        clienteDesatualizado.setDataNascimento(novoCliente.getDataNascimento());
        return clienteDesatualizado;
    }

    public void delete(Integer id) {
        Cliente cliente = listarClienteEspecifico(id);
        listaClientes.remove(cliente);
    }
}
