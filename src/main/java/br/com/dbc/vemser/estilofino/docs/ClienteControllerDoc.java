package br.com.dbc.vemser.estilofino.docs;

import br.com.dbc.vemser.estilofino.dto.cliente.ClienteCreateDTO;
import br.com.dbc.vemser.estilofino.dto.cliente.ClienteDTO;
import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ClienteControllerDoc {

    @Operation(summary = "Listar clientes", description = "Lista todos os clientes do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes();


    @Operation(summary = "Listar cliente específico", description = "Lista um cliente baseado em seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o cliente"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> listarClienteEspecifico(@PathVariable("id") Integer id);


    @Operation(summary = "Criar cliente", description = "Cria um novo cliente no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente (@RequestBody @Valid ClienteCreateDTO dto);


    @Operation(summary = "Editar cliente", description = "Edita um cliente presente no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> editarCliente(@RequestBody @Valid ClienteCreateDTO dto, @PathVariable Integer id) throws RegraDeNegocioException;


    @Operation(summary = "Remover cliente", description = "Remove um cliente do sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente removido"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCliente(@PathVariable("id") Integer id);

}
