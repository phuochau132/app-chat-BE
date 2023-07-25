package com.example.demo.Request;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticationRequest {
   String userName;
   String password;
}
