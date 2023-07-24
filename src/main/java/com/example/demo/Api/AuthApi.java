package com.example.demo.Api;

import com.example.demo.Request.AuthenticationRequest;
import com.example.demo.Response.AuthenticationResponse;
import com.example.demo.Response.ErrorResponse;
import com.example.demo.Response.IErr;
import com.example.demo.Response.TokenResponse;
import com.example.demo.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<IErr> login(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println(authenticationRequest);
        try {
            AuthenticationResponse authenticationResponse = authService.authenticationResponse(authenticationRequest);
            return ResponseEntity.ok(authenticationResponse);
        } catch (Exception e) {
            System.out.println(e);
            ErrorResponse errorResponse = ErrorResponse.builder().err("err").build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
    }

    @PostMapping(value = "/refreshToken")
    public ResponseEntity<IErr> refreshToken(@RequestHeader HttpHeaders headers) {
        String auth = headers.getFirst("Authorization");
        if (auth != null) {
            TokenResponse tokenResponse = authService.refreshToken(auth);
            if (tokenResponse != null) {
                return ResponseEntity.ok(tokenResponse);
            } else {
                return ResponseEntity.status(403).body(ErrorResponse.builder().err("refreshToken dont exits or have been refreshed").build());
            }
        }
        return ResponseEntity.status(403).body(ErrorResponse.builder().err("dont have accessToken").build());
    }
}
