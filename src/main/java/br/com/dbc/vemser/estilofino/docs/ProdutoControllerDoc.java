package br.com.dbc.vemser.estilofino.docs;

import br.com.dbc.vemser.estilofino.dto.produtoDTO.ProdutoCreateDTO;
import br.com.dbc.vemser.estilofino.dto.produtoDTO.ProdutoDTO;
import br.com.dbc.vemser.estilofino.entity.Categoria;
import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProdutoControllerDoc {

    @Operation(summary = "Listar produtos", description = "Lista todos os produtos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de produtos"),
                    @ApiResponse(responseCode = "402", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }

    )
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listar(
            @RequestParam(name = "categoria", required = false) Categoria categoria,
            @RequestParam(name = "cor", required = false) String cor,
            @RequestParam(name = "tamanho", required = false) Double tamanho,
            @RequestParam(name = "minPreco", required = false) Double minPreco,
            @RequestParam(name = "maxPreco", required = false) Double maxPreco) throws RegraDeNegocioException;


    @Operation(summary = "Inserir produto", description = "Inserir produto no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Produto inserido"),
                    @ApiResponse(responseCode = "402", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }

    )
    @PostMapping
    public ResponseEntity<ProdutoDTO> inserir(@Valid @RequestBody ProdutoCreateDTO produto);


    @Operation(summary = "Editar produtos", description = "Inserir produto no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Produto inserido"),
                    @ApiResponse(responseCode = "402", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }

    )
    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> editar(@Min(1) @NotNull @PathVariable("idProduto") Integer id,
                                             @Valid @RequestBody ProdutoCreateDTO produtoAtualizar) throws RegraDeNegocioException;

    @Operation(summary = "Remover produtos", description = "Remover produto do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Produto removido"),
                    @ApiResponse(responseCode = "402", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }

    )
    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> remover(@PathVariable("idProduto") Integer id) throws RegraDeNegocioException;
}
