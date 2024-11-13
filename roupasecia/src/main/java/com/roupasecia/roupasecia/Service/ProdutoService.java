package com.roupasecia.roupasecia.Service;

import com.roupasecia.roupasecia.Entity.Produto;
import com.roupasecia.roupasecia.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    // Criar Produto
    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Listar todos os Produtos
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Buscar Produto por ID
    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    // Atualizar Produto
    public Produto atualizarProduto(Long id, Produto produto) {
        if (produtoRepository.existsById(id)) {
            produto.setIdProduto(id);
            return produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }

    // Deletar Produto
    public boolean deletarProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
