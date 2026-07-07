package com.heladeria.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heladeria.model.Usuario;
import com.heladeria.service.AuthService;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {


    @Autowired
    private AuthService authService;



    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody Usuario usuario
    ){

        return ResponseEntity.ok(
            authService.register(usuario)
        );

    }



    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Usuario usuario
    ){

        return ResponseEntity.ok(
            authService.login(
                usuario.getUsername(),
                usuario.getPassword()
            )
        );

    }

}