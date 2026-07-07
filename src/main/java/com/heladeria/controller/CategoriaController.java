package com.heladeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heladeria.model.Categoria;
import com.heladeria.service.CategoriaService;


@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {


    @Autowired
    private CategoriaService categoriaService;



    // Listar categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {

        return ResponseEntity.ok(
                categoriaService.listar()
        );
    }



    // Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(
            @PathVariable Long id) {


        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    // Registrar categoria
    @PostMapping
    public ResponseEntity<Categoria> guardar(
            @RequestBody Categoria categoria) {


        return ResponseEntity.ok(
                categoriaService.guardar(categoria)
        );
    }



    // Actualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {


        return ResponseEntity.ok(
                categoriaService.actualizar(id, categoria)
        );
    }



    // Eliminar categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {


        categoriaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}