package com.heladeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.heladeria.dto.LoginRequest;
import com.heladeria.dto.LoginResponse;
import com.heladeria.model.Usuario;
import com.heladeria.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ==========================
    // REGISTRAR USUARIO
    // ==========================
    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {

        return authService.register(usuario);

    }

    // ==========================
    // LOGIN
    // ==========================
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return authService.login(
                request.getUsername(),
                request.getPassword());

    }

}