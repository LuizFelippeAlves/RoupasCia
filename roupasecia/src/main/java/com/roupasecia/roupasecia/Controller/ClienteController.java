package com.roupasecia.roupasecia.Controller;

import com.roupasecia.roupasecia.Entity.Cliente;
import com.roupasecia.roupasecia.Repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criar(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void excluir(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }


}
