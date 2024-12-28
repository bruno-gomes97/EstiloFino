package br.com.dbc.vemser.estilofino.controller;

import br.com.dbc.vemser.estilofino.docs.CarrinhoControllerDoc;
import br.com.dbc.vemser.estilofino.dto.carrinho.AdicionarItemDTO;
import br.com.dbc.vemser.estilofino.dto.carrinho.AtualizarItemDTO;
import br.com.dbc.vemser.estilofino.dto.carrinho.CarrinhoDTO;
import br.com.dbc.vemser.estilofino.dto.pedido.PedidoDTO;
import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import br.com.dbc.vemser.estilofino.service.CarrinhoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("carrinho")
@RequiredArgsConstructor
public class CarrinhoController implements CarrinhoControllerDoc {
    private final CarrinhoService carrinhoService;

    @PostMapping()
    public ResponseEntity<CarrinhoDTO> adicionarItem(@RequestBody @Valid AdicionarItemDTO adicionarItemDTO) throws RegraDeNegocioException {
        log.info("Adicionando item");
        CarrinhoDTO c = carrinhoService.adicionarItem(adicionarItemDTO);
        log.info("Item adicionado");
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @GetMapping("{idCliente}")
    public ResponseEntity<CarrinhoDTO> visualizarCarrinho(@PathVariable Integer idCliente) throws RegraDeNegocioException {
        return ResponseEntity.ok().body(carrinhoService.visualizarCarrinho(idCliente));
    }

    @DeleteMapping("{idCliente}")
    public ResponseEntity<Void> removerTodosItens(@PathVariable Integer idCliente) throws RegraDeNegocioException {
        log.info("Esvaziando carrinho");
        carrinhoService.removerTodosItens(idCliente);
        log.info("Carrinho esvaziado com sucesso");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{idCliente}/itens/{idItem}")
    public ResponseEntity<Void> removerItem(@PathVariable Integer idCliente, @PathVariable Integer idItem) throws RegraDeNegocioException {
        log.info("Removendo item");
        carrinhoService.removerItem(idCliente, idItem);
        log.info("Item removido com sucesso");
        return ResponseEntity.ok().build();
    }

    @PutMapping("{idCliente}/itens/{idItem}")
    public ResponseEntity<CarrinhoDTO> editarItem(@RequestBody @Valid AtualizarItemDTO atualizarItemDTO,
                               @PathVariable Integer idCliente,
                               @PathVariable Integer idItem) throws RegraDeNegocioException {
        log.info("Editando item");
        CarrinhoDTO c = carrinhoService.editarItem(atualizarItemDTO, idCliente, idItem);
        log.info("Item editado");
        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/pagamento/{idCliente}/{idEndereco}")
    public ResponseEntity<PedidoDTO> pagarCarrinho(@PathVariable Integer idCliente,
                                                   @PathVariable Integer idEndereco) throws RegraDeNegocioException {
        return new ResponseEntity<>(carrinhoService.pagarCarrinho(idCliente, idEndereco), HttpStatus.OK);
    }
}