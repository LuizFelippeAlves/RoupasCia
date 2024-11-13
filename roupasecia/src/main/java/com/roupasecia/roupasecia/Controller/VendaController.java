package com.roupasecia.roupasecia.Controller;

import com.roupasecia.roupasecia.Entity.Venda;
import com.roupasecia.roupasecia.Repository.VendaRepository;
import com.roupasecia.roupasecia.Service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @Autowired
    private VendaRepository vendaRepository;

    @PostMapping
    public Venda criarVenda(@Valid @RequestBody Venda venda) {
        return vendaService.criarVenda(venda);
    }

    @GetMapping
    public List<Venda> listar() {
        return vendaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Venda buscarPorId(@PathVariable Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    @GetMapping("/{id}/valor-total")
    public BigDecimal valorTotal(@PathVariable Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        return venda.getValorTotal();
    }
}
