package com.heladeria.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.heladeria.service.UsuarioService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFilter extends OncePerRequestFilter {


    @Autowired
    private JwtService jwtService;


    @Autowired
    private UsuarioService usuarioService;



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {


        String header =
                request.getHeader("Authorization");



        if(header != null &&
                header.startsWith("Bearer ")) {


            String token =
                    header.substring(7);



            if(jwtService.validateToken(token)) {


                String username =
                        jwtService.extractUsername(token);



                var usuario =
                        usuarioService
                        .buscarPorUsername(username)
                        .orElse(null);



                if(usuario != null) {


                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    usuario,
                                    null,
                                    null
                            );


                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(auth);
                }
            }
        }



        filterChain.doFilter(
                request,
                response
        );
    }

}
