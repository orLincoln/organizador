package com.pessoal.organizador.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.pessoal.organizador.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String generateToken(User user){
        try{
            Algorithm alg = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(alg);
            return token;
        } catch(JWTCreationException ex){
            throw new RuntimeException("Error while generating auth token", ex);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm alg = Algorithm.HMAC256(secret);
            return JWT.require(alg)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject(); //return null if do not found Subject
        } catch (Exception ex) {
            return "erro na validação do token";
        }
    }


}
