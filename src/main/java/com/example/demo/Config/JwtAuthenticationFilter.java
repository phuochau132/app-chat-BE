package com.example.demo.Config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader("authorization");
        System.out.println(auth);
        if (auth != null && auth.startsWith("Bearer")) {
            try {
                String token = auth.split(" ")[1];
                DecodedJWT decodedJWT = jwtService.verifyToken(token);
                String username = decodedJWT.getSubject();
                String[] role = decodedJWT.getClaim("roles").asArray(String.class);
                Collection<SimpleGrantedAuthority> collection = new ArrayList<>();
                Arrays.stream(role).forEach(item -> collection.add(new SimpleGrantedAuthority(item)));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, collection);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                System.out.println("refreshToken hậu nè");
                response.setStatus(401);
            }
        } else {
            System.out.println(1235);
            filterChain.doFilter(request, response);
        }
    }
}
