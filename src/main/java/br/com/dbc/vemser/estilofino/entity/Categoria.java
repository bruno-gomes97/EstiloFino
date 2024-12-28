package br.com.dbc.vemser.estilofino.entity;

import java.util.Arrays;

public enum Categoria {
    ROUPAS_FEMININAS(1),
    ROUPAS_MASCULINAS(2),
    MODA_PRAIA(3),
    SAPATOS(4);

    private final Integer id;

    Categoria(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static Categoria ofId(Integer id) {
        return Arrays.stream(Categoria.values())
                .filter(categoria -> categoria.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada para o id: " + id));
    }
}