package br.com.dbc.vemser.estilofino.controller;

import br.com.dbc.vemser.estilofino.docs.ClienteControllerDoc;
import br.com.dbc.vemser.estilofino.dto.cliente.ClienteCreateDTO;
import br.com.dbc.vemser.estilofino.dto.cliente.ClienteDTO;
import br.com.dbc.vemser.estilofino.dto.pedido.PedidoDTO;
import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import br.com.dbc.vemser.estilofino.service.ClienteService;
import br.com.dbc.vemser.estilofino.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@Slf4j
@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController implements ClienteControllerDoc {

    private final ClienteService service;
    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> lista = service.listarClientes();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> listarClienteEspecifico(@PathVariable("id") Integer id) {
        ClienteDTO dto = service.listarClienteEspecifico(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente (@RequestBody @Valid ClienteCreateDTO dto) {
        log.info("Criando cliente");
        ClienteDTO cliente = service.criarCliente(dto);
        log.info("Cliente criado com sucesso");
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> editarCliente(@RequestBody @Valid ClienteCreateDTO dto, @PathVariable Integer id) throws RegraDeNegocioException {
        log.info("Editando cliente");
        ClienteDTO cliente = service.editarCliente(dto, id);
        log.info("Cliente editado com sucesso");
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCliente(@PathVariable("id") Integer id) {
        log.info("Removendo cliente");
        service.removerCliente(id);
        log.info("Cliente removido com sucesso");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<List<PedidoDTO>> listarPedidosPorIdCliente(@PathVariable("id") Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(pedidoService.listarPedidosPorIdCliente(id), HttpStatus.OK);
    }
}
