package com.heladeria.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.heladeria.model.Usuario;
import com.heladeria.repository.UsuarioRepository;


@Service
public class AuthService {


    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;



    // ==========================
    // REGISTRAR USUARIO
    // ==========================
    public Usuario register(Usuario usuario){


        // Verificar si el username ya existe
        if(usuarioRepository
                .findByUsername(usuario.getUsername())
                .isPresent()){


            throw new RuntimeException(
                    "El usuario ya existe"
            );

        }



        // Encriptar contraseña
        usuario.setPassword(
                passwordEncoder.encode(
                        usuario.getPassword()
                )
        );



        // Rol por defecto
        if(usuario.getRol()==null ||
           usuario.getRol().isEmpty()){


            usuario.setRol("USER");

        }



        return usuarioRepository.save(usuario);

    }





    // ==========================
    // LOGIN DE USUARIO
    // ==========================
    public String login(
            String username,
            String password
    ){


        // Buscar usuario por username
        Usuario usuario = usuarioRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Usuario no encontrado"
                        )
                );



        // Comparar contraseña ingresada
        // con contraseña encriptada
        if(!passwordEncoder.matches(
                password,
                usuario.getPassword()
        )){


            throw new RuntimeException(
                    "Contraseña incorrecta"
            );

        }



        return "Login correcto";

    }


}