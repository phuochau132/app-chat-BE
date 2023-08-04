package com.example.demo.Repositories;


import com.example.demo.Entity.ImgPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagePostRepository extends JpaRepository<ImgPostEntity, Long> {
}