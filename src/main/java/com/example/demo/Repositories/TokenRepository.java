package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.TokenEntity;
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByAccessToken(String accessToken);
    long deleteByAccessToken(String accessToken);
}