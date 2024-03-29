package com.example.demo.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Custom.RoleCusTomResponse;
import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.TokenEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repositories.TokenRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Request.AuthenticationRequest;
import com.example.demo.Response.AuthenticationResponse;
import com.example.demo.Response.TokenResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final RoleCusTomResponse roleCusTomResponse;
    private final JwtService jwtService;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public AuthenticationResponse authenticationResponse(AuthenticationRequest authenticationRequest) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
            UserEntity user = userRepository.findByName(authenticationRequest.getUserName()).orElseThrow();
            List<RoleEntity> role = null;
            if (user != null) {
                role = roleCusTomResponse.getRole(user);
            }
            Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
            Set<RoleEntity> set = new HashSet<>();
            role.stream().forEach(item -> {
                authority.add(new SimpleGrantedAuthority(item.getName()));
            });
            var jwtToken = jwtService.generateToken(user.getName(), authority);
            var jwtRefreshToken = jwtService.generateRefreshToken(user.getName(), authority);
            tokenRepository.save(new TokenEntity(jwtToken, jwtRefreshToken));
            return AuthenticationResponse.builder().accessToken(jwtToken).user(user).build();
    }
    @Transactional
    public TokenResponse refreshToken(String auth) {
        String token = auth.split(" ")[1];
        TokenEntity tokenEntity = tokenRepository.findByAccessToken(token);
        if (tokenEntity != null) {
            DecodedJWT decodedJWT = jwtService.verifyToken(tokenEntity.getRefreshToken());
            String email = decodedJWT.getSubject();
            String[] role = decodedJWT.getClaim("roles").asArray(String.class);
            Collection<SimpleGrantedAuthority> collection = new ArrayList<>();
            Arrays.stream(role).forEach(item -> collection.add(new SimpleGrantedAuthority(item)));
            String newAccessToken = jwtService.generateToken(email, collection);
            String newRefreshToken = jwtService.generateRefreshToken(email, collection);
            tokenRepository.delete(tokenEntity);
            tokenRepository.save(TokenEntity.builder().refreshToken(newRefreshToken).accessToken(newAccessToken).build());
            return TokenResponse.builder().accessToken(newAccessToken).refreshToken(newRefreshToken).build();
        } else {
            return null;
        }
    }
}
