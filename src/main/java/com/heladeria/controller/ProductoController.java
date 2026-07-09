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

    // ===========================
    // LISTAR TODOS
    // ===========================
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {

        return ResponseEntity.ok(
                productoService.listar()
        );

    }

    // ===========================
    // BUSCAR POR ID
    // ===========================
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {

        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    // ===========================
    // GUARDAR PRODUCTO
    // ===========================
    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {

        System.out.println("==================================");
        System.out.println("NUEVO PRODUCTO RECIBIDO");
        System.out.println("Nombre: " + producto.getNombre());
        System.out.println("Descripción: " + producto.getDescripcion());
        System.out.println("Precio: " + producto.getPrecio());
        System.out.println("Stock: " + producto.getStock());
        System.out.println("Sabor: " + producto.getSabor());

        if (producto.getCategoria() == null) {

            System.out.println("Categoría = NULL");

        } else {

            System.out.println("Categoría ID = " + producto.getCategoria().getId());

        }

        Producto nuevoProducto = productoService.guardar(producto);

        return ResponseEntity.ok(nuevoProducto);

    }

    // ===========================
    // ACTUALIZAR PRODUCTO
    // ===========================
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Long id,
            @RequestBody Producto producto) {

        Producto actualizado = productoService.actualizar(id, producto);

        return ResponseEntity.ok(actualizado);

    }

    // ===========================
    // ELIMINAR PRODUCTO
    // ===========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        productoService.eliminar(id);

        return ResponseEntity.noContent().build();

    }

}