package com.heladeria.service;

import com.heladeria.dto.LoginRequest;
import com.heladeria.dto.LoginResponse;
import com.heladeria.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public LoginResponse login(LoginRequest request){

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getUsuario(),
                        request.getPassword())

        );

        String token =
                jwtService.generateToken(request.getUsuario());

        return new LoginResponse(token);

    }

}