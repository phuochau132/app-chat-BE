package com.example.demo.Repositories;

import com.example.demo.Entity.CommentEntity;
import com.example.demo.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    Collection<CommentEntity> findByPost(PostEntity post);
}