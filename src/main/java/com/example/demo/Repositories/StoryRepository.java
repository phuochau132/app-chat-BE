package com.example.demo.Repositories;

import com.example.demo.Entity.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<StoryEntity, Long> {

}