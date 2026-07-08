package com.heladeria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.heladeria.dto.LoginResponse;
import com.heladeria.model.Usuario;
import com.heladeria.repository.UsuarioRepository;
import com.heladeria.security.JwtService;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // ==========================
    // REGISTRAR USUARIO
    // ==========================
    public Usuario register(Usuario usuario) {

        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        usuario.setPassword(
                passwordEncoder.encode(usuario.getPassword())
        );

        if (usuario.getRol() == null || usuario.getRol().isBlank()) {
            usuario.setRol("USER");
        }

        return usuarioRepository.save(usuario);
    }

    // ==========================
    // LOGIN
    // ==========================
    public LoginResponse login(String username, String password) {

        Usuario usuario = usuarioRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(usuario.getUsername());

        return new LoginResponse(token);
    }

}