package br.com.dbc.vemser.estilofino.docs;

import br.com.dbc.vemser.estilofino.dto.carrinho.AdicionarItemDTO;
import br.com.dbc.vemser.estilofino.dto.carrinho.AtualizarItemDTO;
import br.com.dbc.vemser.estilofino.dto.carrinho.CarrinhoDTO;
import br.com.dbc.vemser.estilofino.dto.pedido.PedidoDTO;
import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface CarrinhoControllerDoc {

    @Operation(summary = "Adicionar item", description = "Adiciona um ítem no carrinho")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Item adicionado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping()
    public ResponseEntity<CarrinhoDTO> adicionarItem(@RequestBody @Valid AdicionarItemDTO adicionarItemDTO) throws RegraDeNegocioException;

    @Operation(summary = "Visualiza carrinho de um cliente", description = "Com base em um ID, é visualizado o carrinho de um cliente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Itens listados"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("{idCliente}")
    public ResponseEntity<CarrinhoDTO> visualizarCarrinho(@PathVariable Integer idCliente) throws RegraDeNegocioException;

    @Operation(summary = "Esvazia o carrinho", description = "Limpar todos os itens do carrinho")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Carrinho esvaziado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("{idCliente}")
    public ResponseEntity<Void> removerTodosItens(@PathVariable Integer idCliente) throws RegraDeNegocioException;

    @Operation(summary = "Remover item", description = "Remove um item do carrinho de um cliente específico")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Item removido"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("{idCliente}/itens/{idItem}")
    public ResponseEntity<Void> removerItem(@PathVariable Integer idCliente, @PathVariable Integer idItem) throws RegraDeNegocioException;

    @Operation(summary = "Editar item", description = "Edita um item do carrinho de um cliente específico")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Item adicionado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("{idCliente}/itens/{idItem}")
    public ResponseEntity<CarrinhoDTO> editarItem(@RequestBody @Valid AtualizarItemDTO atualizarItemDTO,
                                                  @PathVariable Integer idCliente,
                                                  @PathVariable Integer idItem) throws RegraDeNegocioException;

    @Operation(summary = "Pagar carrinho", description = "Paga um carrinho de um cliente e envia para o endereço selecionado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Carrinho pago e enviado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/pagamento/{idCliente}/{idEndereco}")
    public ResponseEntity<PedidoDTO> pagarCarrinho(@PathVariable Integer idCliente,
                                                   @PathVariable Integer idEndereco) throws RegraDeNegocioException;
}
