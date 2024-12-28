package br.com.dbc.vemser.estilofino.entity;

import java.util.Arrays;

public enum StatusProduto {
    DISPONIVEL(1),
    INDISPONIVEL(2);

    private final Integer codigo;

    StatusProduto(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static StatusProduto ofCodigo(Integer codigo) {
        return Arrays.stream(StatusProduto.values())
                .filter(status -> status.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Status não encontrado para o código: " + codigo));
    }
}