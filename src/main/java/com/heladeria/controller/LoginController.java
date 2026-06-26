package com.heladeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heladeria.dto.LoginRequest;
import com.heladeria.dto.LoginResponse;
import com.heladeria.service.UsuarioService;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        LoginResponse response = service.login(request);

        if ("Inicio de sesión exitoso".equals(response.getMensaje())) {

            return ResponseEntity.ok(response);

        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);

    }

}