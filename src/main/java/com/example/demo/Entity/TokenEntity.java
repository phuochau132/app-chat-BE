package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="token")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String accessToken;

    @Column(length = 255)
    private String refreshToken;

    public TokenEntity(String accessToken, String refreshToken) {
        this.accessToken=accessToken;
        this.refreshToken=refreshToken;

    }
}
