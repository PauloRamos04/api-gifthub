package com.example.GiftHub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.GiftHub.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);


    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    private HttpServletRequest request;

    public String generateToken(User user) {
        HttpSession session = request.getSession();
        // Verifica se o token já existe na sessão
        String existingToken = (String) session.getAttribute("token");
        if (existingToken != null) {
            return existingToken;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            session.setAttribute("token", token);
            return token;
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error while generating token", ex);
        }
    }

    public String validateToken(String token){
        try{
           Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.require(algorithm)
                   .withIssuer("auth-api")
                   .build()
                   .verify(token)
                   .getSubject();
        } catch (JWTVerificationException ex) {
            return "";
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
