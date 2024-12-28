package br.com.dbc.vemser.estilofino.controller;

import br.com.dbc.vemser.estilofino.docs.EnderecoControllerDoc;
import br.com.dbc.vemser.estilofino.dto.enderecoDTO.EnderecoDTO;
import br.com.dbc.vemser.estilofino.dto.enderecoDTO.EnderecoCreateDTO;

import br.com.dbc.vemser.estilofino.exception.RegraDeNegocioException;
import br.com.dbc.vemser.estilofino.service.EnderecoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/endereco")
public class EnderecoController implements EnderecoControllerDoc {
    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> list(){
        List<EnderecoDTO> listaEnderecos = enderecoService.list();
        return new ResponseEntity<>(listaEnderecos,HttpStatus.OK);
    }

    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<List<EnderecoDTO>> listByIdPessoa(@PathVariable("idPessoa") Integer idPessoa) throws RegraDeNegocioException {
        List<EnderecoDTO> enderecoList = enderecoService.listByIdPessoa(idPessoa);
        return new ResponseEntity<>(enderecoList,HttpStatus.OK);
    }

    @PostMapping("{idPessoa}")
    public ResponseEntity<EnderecoDTO> create(@Validated @PathVariable("idPessoa") Integer id,
                                              @RequestBody EnderecoCreateDTO enderecoCriar) throws RegraDeNegocioException {
        log.info("Criando endereço");
        EnderecoDTO enderecoDTO = enderecoService.create(id,enderecoCriar);
        log.info("Endereço criado com sucesso");
        return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> update(@Min(1) @NotNull @PathVariable("idEndereco") Integer id,
                                              @Valid @RequestBody EnderecoCreateDTO enderecoAtualizar) throws RegraDeNegocioException{
        log.info("Atualizando endereço");
        EnderecoDTO enderecoDTO = enderecoService.update(id,enderecoAtualizar);
        log.info("Endereço atualizado com sucesso");
        return new ResponseEntity<>(enderecoDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable("idEndereco") Integer id) throws RegraDeNegocioException {
        log.info("Deletando endereço");
        enderecoService.delete(id);
        log.info("Endereço deletado com sucesso");
        return ResponseEntity.ok().build();
    }
}
