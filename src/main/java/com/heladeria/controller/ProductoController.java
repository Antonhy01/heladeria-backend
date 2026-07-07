package com.heladeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heladeria.model.Producto;
import com.heladeria.service.ProductoService;


@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {


    @Autowired
    private ProductoService productoService;



    // Listar productos
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {

        return ResponseEntity.ok(
                productoService.listar()
        );
    }



    // Buscar producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(
            @PathVariable Long id) {


        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    // Crear producto
    @PostMapping
    public ResponseEntity<Producto> guardar(
            @RequestBody Producto producto) {


        return ResponseEntity.ok(
                productoService.guardar(producto)
        );
    }



    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Long id,
            @RequestBody Producto producto) {


        return ResponseEntity.ok(
                productoService.actualizar(id, producto)
        );
    }



    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {


        productoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}