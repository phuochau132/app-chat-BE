package com.example.demo.Response;
import com.example.demo.Entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticationResponse implements IEmpty {
    private UserEntity user;
    private String accessToken;
}
