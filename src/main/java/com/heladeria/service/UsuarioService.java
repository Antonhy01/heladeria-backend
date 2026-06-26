package com.heladeria.service;

import java.util.List;

import com.heladeria.dto.LoginRequest;
import com.heladeria.dto.LoginResponse;
import com.heladeria.model.Usuario;

public interface UsuarioService {

    List<Usuario> listar();

    Usuario buscarPorId(Long id);

    Usuario guardar(Usuario usuario);

    Usuario actualizar(Long id, Usuario usuario);

    void eliminar(Long id);

    LoginResponse login(LoginRequest request);

}