package com.heladeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heladeria.model.Venta;
import com.heladeria.service.VentaService;


@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {


    @Autowired
    private VentaService ventaService;



    // Listar todas las ventas
    @GetMapping
    public ResponseEntity<List<Venta>> listar() {

        return ResponseEntity.ok(
                ventaService.listar()
        );
    }



    // Buscar venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(
            @PathVariable Long id) {


        return ventaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    // Registrar venta
    @PostMapping
    public ResponseEntity<Venta> guardar(
            @RequestBody Venta venta) {


        return ResponseEntity.ok(
                ventaService.guardar(venta)
        );
    }



    // Actualizar venta
    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(
            @PathVariable Long id,
            @RequestBody Venta venta) {


        return ResponseEntity.ok(
                ventaService.actualizar(id, venta)
        );
    }



    // Eliminar venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {


        ventaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}