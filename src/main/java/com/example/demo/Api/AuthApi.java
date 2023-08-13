package com.example.demo.Api;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Request.AuthenticationRequest;
import com.example.demo.Response.AuthenticationResponse;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
import com.example.demo.Response.TokenResponse;
import com.example.demo.Service.AuthService;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;
    private final UserService userService;

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
    public ResponseEntity<String> register(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            userService.saveUser(new UserEntity(authenticationRequest.getUserName(),authenticationRequest.getPassword()));
            return ResponseEntity.ok("register success");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body("register dont success");
        }
    }
}
