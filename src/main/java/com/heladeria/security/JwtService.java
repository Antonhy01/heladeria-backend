package com.heladeria.security;


import java.util.Date;


import javax.crypto.SecretKey;


import org.springframework.stereotype.Service;


import com.heladeria.model.Usuario;


import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;



@Service
public class JwtService {



    private static final String SECRET_KEY =

            "heladeria-sistema-seguro-token-jwt-java17-2026-clave-muy-larga";





    private SecretKey getKey(){


        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );


    }







    public String generateToken(
            Usuario usuario
    ){



        return Jwts.builder()


                .subject(
                    usuario.getUsername()
                )


                .claim(
                    "rol",
                    usuario.getRol()
                )


                .issuedAt(
                    new Date()
                )


                .expiration(

                    new Date(

                    System.currentTimeMillis()
                    +
                    86400000

                    )

                )


                .signWith(
                    getKey()
                )


                .compact();



    }







    public String extractUsername(
            String token
    ){


        return Jwts.parser()


                .verifyWith(
                    getKey()
                )


                .build()


                .parseSignedClaims(
                    token
                )


                .getPayload()


                .getSubject();


    }








    public boolean validateToken(
            String token
    ){


        try{


            Jwts.parser()

            .verifyWith(
                getKey()
            )

            .build()

            .parseSignedClaims(
                token
            );



            return true;



        }catch(Exception e){


            return false;


        }


    }


}