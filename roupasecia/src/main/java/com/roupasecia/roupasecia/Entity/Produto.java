package com.roupasecia.roupasecia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    @NotBlank
    private String descricao;

    @Positive
    private BigDecimal preco;

    @PositiveOrZero // Vai ajudar na validação e tratativa de erro
    private Integer quantidadeEstoque;


    @ManyToMany
    @JoinTable(
            name = "produto_categoria",  // Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "id_produto"),  // Chave estrangeira de Produto
            inverseJoinColumns = @JoinColumn(name = "id_categoria")  // Chave estrangeira de Categoria
    )
    private List<Categoria> categoria; // criar a categoria <--

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }
}
