package com.example.demo.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponse implements IEmpty {
    private String accessToken;
    private String refreshToken;


}
