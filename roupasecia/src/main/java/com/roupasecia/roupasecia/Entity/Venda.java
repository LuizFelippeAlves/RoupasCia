package com.roupasecia.roupasecia.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenda;

    private LocalDate data;

    @ManyToOne // olha esses cara aqui, é uma anotação explicita Muitos para  1 ou o oposto
    @JoinColumn(name = "idCliente") // join faz a junção da informaçao de dados com outra tabela !! :D
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL) // <----- é importante manjar disso quando for mexer com banco
    @JsonManagedReference // Adiciona a anotação aqui
    private List<VendaProdutos> vendaProdutos; // criaremos essa operação de venda !

    //Pegar o valor total da venda
    public BigDecimal getValorTotal() {
        return vendaProdutos.stream()
                .map(vp -> {
                    BigDecimal preco = vp.getProduto().getPreco();
                    if (preco == null) {
                        return BigDecimal.ZERO; // Se o preço for nulo, retorna zero
                    }
                    return preco.multiply(BigDecimal.valueOf(vp.getQuantidade()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<VendaProdutos> getVendaProdutos() {
        return vendaProdutos;
    }

    public void setVendaProdutos(List<VendaProdutos> vendaProdutos) {
        this.vendaProdutos = vendaProdutos;
    }
}
