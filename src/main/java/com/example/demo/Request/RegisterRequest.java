package com.example.demo.Request;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterRequest {
    String userName;
    String password;
    String fullName;
    String email;
}
