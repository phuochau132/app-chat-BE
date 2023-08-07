package com.example.demo.Custom;

import com.example.demo.Entity.PostEntity;
import com.example.demo.Entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class PostCusTomResponse {
    @PersistenceContext
    private EntityManager entityManager;

    public Set<UserEntity> getUserLiked(PostEntity post) {
        String sql = "SELECT u FROM UserEntity u " +
                "JOIN u.posts r " +
                "WHERE r.id = :id";

        TypedQuery<UserEntity> query = entityManager.createQuery(sql, UserEntity.class);
        query.setParameter("id", post.getId());

        List<UserEntity> users = query.getResultList();
        return new HashSet<>(users);
    }
    public Set<PostEntity> getPosts(long idPost) {
        String sql = "SELECT p FROM PostEntity p " +
                "WHERE p.id > :id ORDER BY p.id ASC";
        TypedQuery<PostEntity> query = entityManager.createQuery(sql, PostEntity.class);
        query.setParameter("id", idPost);
        query.setMaxResults(2);
        List<PostEntity> posts = query.getResultList();
        return new HashSet<>(posts);
    }

}


