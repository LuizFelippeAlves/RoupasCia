package com.roupasecia.roupasecia.Controller;

import com.roupasecia.roupasecia.Entity.Categoria;
import com.roupasecia.roupasecia.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    // Listar todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarTodas();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.buscarPorId(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar nova categoria
    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = categoriaService.salvar(categoria);
        return new ResponseEntity<>(categoriaSalva, HttpStatus.CREATED);
    }

    // Atualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Optional<Categoria> categoriaExistente = categoriaService.buscarPorId(id);
        if (categoriaExistente.isPresent()) {
            categoria.setIdCategoria(id);
            Categoria categoriaAtualizada = categoriaService.salvar(categoria);
            return new ResponseEntity<>(categoriaAtualizada, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    // Deletar categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        Optional<Categoria> categoriaExistente = categoriaService.buscarPorId(id);
        if (categoriaExistente.isPresent()) {
            categoriaService.deletar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
    }
}
