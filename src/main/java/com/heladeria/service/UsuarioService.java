package com.heladeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.heladeria.model.Usuario;
import com.heladeria.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Usuario buscarPorUsername(String username) {
        return repository.findByUsername(username);
    }

    public Usuario guardar(Usuario usuario) {

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return repository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuario) {

        Usuario existente = repository.findById(id).orElse(null);

        if (existente != null) {

            existente.setNombre(usuario.getNombre());
            existente.setUsername(usuario.getUsername());

            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }

            existente.setRol(usuario.getRol());

            return repository.save(existente);
        }

        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}