package com.example.demo.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private static final String secret__key="hau123";
    public String generateToken(String email, Collection<SimpleGrantedAuthority> collection){
        Algorithm algorithm=Algorithm.HMAC256(secret__key.getBytes());
        return JWT.create().withSubject(email).withExpiresAt(new Date(System.currentTimeMillis()+50)).withClaim("roles",collection.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).sign(algorithm);

    }
    public String generateRefreshToken(String email, Collection<SimpleGrantedAuthority> collection){
        Algorithm algorithm=Algorithm.HMAC256(secret__key.getBytes());
        return JWT.create().withSubject(email).withExpiresAt(new Date(System.currentTimeMillis() + (long) Integer.MAX_VALUE * 1000)).withClaim("roles",collection.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).sign(algorithm);

    }
    public DecodedJWT verifyToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret__key.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }
}
