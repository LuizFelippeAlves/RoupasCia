package com.roupasecia.roupasecia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Categoria {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @NotBlank
    private String descricao;

    @ManyToMany(mappedBy = "categoria")
    private List<Produto> produtos;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
