package br.com.dbc.vemser.estilofino.controller;

import br.com.dbc.vemser.estilofino.docs.ProdutoControllerDoc;
import br.com.dbc.vemser.estilofino.dto.produtoDTO.ProdutoCreateDTO;
import br.com.dbc.vemser.estilofino.dto.produtoDTO.ProdutoDTO;
import br.com.dbc.vemser.estilofino.entity.Categoria;
import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import br.com.dbc.vemser.estilofino.service.ProdutoService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController implements ProdutoControllerDoc {
    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listar(
            @RequestParam(name = "categoria", required = false) Categoria categoria,
            @RequestParam(name = "cor", required = false) String cor,
            @RequestParam(name = "tamanho", required = false) Double tamanho,
            @RequestParam(name = "minPreco", required = false) Double minPreco,
            @RequestParam(name = "maxPreco", required = false) Double maxPreco) throws RegraDeNegocioException {
        return new ResponseEntity<>(produtoService.listarTodos(categoria, cor, tamanho, minPreco, maxPreco), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> inserir(@Valid @RequestBody ProdutoCreateDTO produto){
        log.info("Criando produto");
        ProdutoDTO produtoCriado = produtoService.inserir(produto);
        log.info("Produto criado com sucesso");
        return new ResponseEntity<>(produtoCriado, HttpStatus.OK);
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> editar(@Min(1) @NotNull @PathVariable("idProduto") Integer id,
                                            @Valid @RequestBody ProdutoCreateDTO produtoAtualizar) throws RegraDeNegocioException {
        log.info("Atualizando produto");
        ProdutoDTO produtoAtualizado = produtoService.editar(id, produtoAtualizar);
        log.info("Produto atualizado com sucesso");
        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> remover(@PathVariable("idProduto") Integer id) throws RegraDeNegocioException {
        log.info("Deletando produto");
        produtoService.remover(id);
        log.info("Produto deletado com sucesso");
        return ResponseEntity.ok().build();
    }
}
