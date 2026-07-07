package com.heladeria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Usuario;
import com.heladeria.repository.UsuarioRepository;


@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;



    public Optional<Usuario> buscarPorUsername(String username) {

        return usuarioRepository.findByUsername(username);

    }


    public Usuario guardar(Usuario usuario) {

        return usuarioRepository.save(usuario);

    }


}