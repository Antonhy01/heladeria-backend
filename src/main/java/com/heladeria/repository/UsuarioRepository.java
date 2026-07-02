package com.heladeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heladeria.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Usuario findByUsername(String username);

}