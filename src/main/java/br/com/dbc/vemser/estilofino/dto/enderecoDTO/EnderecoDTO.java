package br.com.dbc.vemser.estilofino.dto.enderecoDTO;
import lombok.Data;

@Data
public class EnderecoDTO{
    private Integer idEndereco;
    private Integer idCliente;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String cep;
}
