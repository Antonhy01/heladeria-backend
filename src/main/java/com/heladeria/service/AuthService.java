package com.heladeria.service;

import com.heladeria.dto.LoginRequest;
import com.heladeria.dto.LoginResponse;
import com.heladeria.model.Usuario;
import com.heladeria.repository.UsuarioRepository;
import com.heladeria.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getUsuario(),
                        request.getPassword())

        );

        Usuario usuario = usuarioRepository
                .findByUsuario(request.getUsuario())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(usuario.getUsuario());

        return new LoginResponse(
                token,
                usuario.getId(),
                usuario.getNombre(),
                usuario.getUsuario(),
                usuario.getRol(),
                "Inicio de sesión correcto"
        );

    }

}