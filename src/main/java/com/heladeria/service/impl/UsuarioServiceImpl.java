package com.heladeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.heladeria.dto.LoginRequest;
import com.heladeria.dto.LoginResponse;
import com.heladeria.model.Usuario;
import com.heladeria.repository.UsuarioRepository;
import com.heladeria.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Usuario guardar(Usuario usuario) {

        // Encriptar contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return repository.save(usuario);
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {

        Usuario existente = repository.findById(id).orElse(null);

        if (existente != null) {

            existente.setNombre(usuario.getNombre());
            existente.setUsuario(usuario.getUsuario());

            if (usuario.getPassword() != null &&
                !usuario.getPassword().isEmpty()) {

                existente.setPassword(
                        passwordEncoder.encode(usuario.getPassword()));
            }

            existente.setRol(usuario.getRol());

            return repository.save(existente);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Usuario usuario = repository
                .findByUsuario(request.getUsuario())
                .orElse(null);

        if (usuario == null) {

            return new LoginResponse(
                    null,
                    null,
                    null,
                    null,
                    "Usuario no encontrado");
        }

        boolean correcto = passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword());

        if (!correcto) {

            return new LoginResponse(
                    null,
                    null,
                    null,
                    null,
                    "Contraseña incorrecta");
        }

        return new LoginResponse(

                usuario.getId(),
                usuario.getNombre(),
                usuario.getUsuario(),
                usuario.getRol(),
                "Inicio de sesión exitoso"

        );

    }

}