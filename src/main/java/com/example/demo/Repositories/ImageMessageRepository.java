package com.example.demo.Repositories;


import com.example.demo.Entity.ImageMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMessageRepository extends JpaRepository<ImageMessageEntity, Long> {
}