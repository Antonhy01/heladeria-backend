package com.heladeria.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.heladeria.model.Usuario;
import com.heladeria.service.UsuarioService;



@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;



    // Listar usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){

        return ResponseEntity.ok(
            usuarioService.listar()
        );
    }



    // Buscar usuario
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(
            @PathVariable Long id){

        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(
                    ResponseEntity.notFound().build()
                );
    }



    // Crear usuario
    @PostMapping
    public ResponseEntity<Usuario> guardar(
            @RequestBody Usuario usuario){

        return ResponseEntity.ok(
            usuarioService.guardar(usuario)
        );
    }



    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(
            @PathVariable Long id,
            @RequestBody Usuario usuario){


        return ResponseEntity.ok(
            usuarioService.actualizar(id, usuario)
        );
    }



    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id){


        usuarioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}