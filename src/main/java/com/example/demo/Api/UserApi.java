package com.example.demo.Api;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Request.AuthenticationRequest;
import com.example.demo.Response.IErr;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<Collection<UserEntity>> getAllUser() {
        System.out.println(123);
        try {
            return ResponseEntity.ok(userService.getAllUser());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(null);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            userService.saveUser(new UserEntity(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
            return ResponseEntity.ok("register success");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body("register dont success");
        }
    }
}