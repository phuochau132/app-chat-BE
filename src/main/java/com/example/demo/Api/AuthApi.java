package com.example.demo.Api;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Request.AuthenticationRequest;
import com.example.demo.Request.ForgotPasswordRequest;
import com.example.demo.Request.RegisterRequest;
import com.example.demo.Request.ResetPasswordRequest;
import com.example.demo.Response.AuthenticationResponse;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
import com.example.demo.Response.TokenResponse;
import com.example.demo.Service.AuthService;
import com.example.demo.Service.EmailService;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;
    private final UserService userService;
    private final EmailService emailService;
    private final JwtService jwtService;

    @PostMapping(value = "/login")
    public ResponseEntity<IEmpty> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            AuthenticationResponse authenticationResponse = authService.authenticationResponse(authenticationRequest);
            return ResponseEntity.ok(authenticationResponse);
        } catch (Exception e) {
            System.out.println(e);
            EmptyResponse errorResponse = EmptyResponse.builder().message("err").build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
    }

    @PostMapping(value = "/refreshToken")
    public ResponseEntity<IEmpty> refreshToken(@RequestHeader HttpHeaders headers) {
        String auth = headers.getFirst("Authorization");
        if (auth != null) {
            TokenResponse tokenResponse = authService.refreshToken(auth);
            if (tokenResponse != null) {
                return ResponseEntity.ok(tokenResponse);
            } else {
                return ResponseEntity.status(403).body(EmptyResponse.builder().message("refreshToken dont exits or have been refreshed").build());
            }
        }

        return ResponseEntity.status(403).body(EmptyResponse.builder().message("dont have accessToken").build());
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        System.out.println(registerRequest);
        try {
            userService.saveUser(new UserEntity(registerRequest.getUserName(),registerRequest.getPassword(),registerRequest.getEmail(),registerRequest.getFullName()));
            return ResponseEntity.ok("register success");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body("register dont success");
        }
    }
    @PostMapping(value = "/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        try {
            UserEntity user = userService.findByEmail(forgotPasswordRequest.getEmail());
            Collection<SimpleGrantedAuthority> authorities = user.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                    .collect(Collectors.toList());
            String token = jwtService.generateEmailToken(user.getEmail(), authorities);
            emailService.sendForgotPasswordConfirmation(forgotPasswordRequest.getEmail(), token);
            return ResponseEntity.ok("Vui lòng kiểm tra email để đổi mật khẩu.");
        }catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/reset-password")
    public String getResetPasswordTemplate(@RequestParam String token, Model model) {
        try {
            DecodedJWT decodedJWT = jwtService.verifyToken(token);
            String email = decodedJWT.getSubject();
            model.addAttribute("email", email);
            return "reset-password";
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }
    @PostMapping(value = "/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        try {
            userService.changePassword(resetPasswordRequest.getEmail(),resetPasswordRequest.getPassword());
            return ResponseEntity.ok("Đổi mật khẩu thành công.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body("Đổi mật khẩu không thành công.");
        }
    }
}
