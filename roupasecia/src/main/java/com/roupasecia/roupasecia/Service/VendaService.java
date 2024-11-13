package com.roupasecia.roupasecia.Service;

import com.roupasecia.roupasecia.Entity.Produto;
import com.roupasecia.roupasecia.Entity.Venda;
import com.roupasecia.roupasecia.Repository.ProdutoRepository;
import com.roupasecia.roupasecia.Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    //criar venda agora !

    public Venda criarVenda(Venda venda) {
        // Para cada produto na venda, validamos o estoque e atualizamos
        venda.getVendaProdutos().forEach(vp -> {
            Produto produto = produtoRepository.findById(vp.getProduto().getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            // Verificar se há estoque suficiente para o produto
            if (produto.getQuantidadeEstoque() < vp.getQuantidade()) {
                throw new RuntimeException("Quantidade insuficiente em estoque");
            }

            // Atualizar quantidade no estoque
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - vp.getQuantidade());
            produtoRepository.save(produto); // Salvar o produto atualizado

            // Associar a venda ao item de venda (VendaProdutos)
            vp.setVenda(venda); // Associando a venda ao item
        });

        // Agora, vamos salvar todos os itens de VendaProdutos
        // A persistência dos itens de VendaProdutos é garantida por cascata se você já tiver a anotação @OneToMany com CascadeType.ALL
        return vendaRepository.save(venda); // Salva a venda, que inclui a lista de vendaProdutos
    }
}
