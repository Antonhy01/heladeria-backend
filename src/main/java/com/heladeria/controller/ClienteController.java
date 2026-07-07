package com.heladeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heladeria.model.Cliente;
import com.heladeria.service.ClienteService;


@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;



    // Listar clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {

        return ResponseEntity.ok(
                clienteService.listar()
        );
    }



    // Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(
            @PathVariable Long id) {


        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    // Registrar cliente
    @PostMapping
    public ResponseEntity<Cliente> guardar(
            @RequestBody Cliente cliente) {


        return ResponseEntity.ok(
                clienteService.guardar(cliente)
        );
    }



    // Actualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(
            @PathVariable Long id,
            @RequestBody Cliente cliente) {


        return ResponseEntity.ok(
                clienteService.actualizar(id, cliente)
        );
    }



    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {


        clienteService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}