package com.example.demo.Custom;

import com.example.demo.Entity.CommentEntity;
import com.example.demo.Entity.ImgPostEntity;
import com.example.demo.Entity.PostEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Response.PostResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Transactional
    public Set<PostResponse> getPosts(long idPost) {
        String sql1 = "SELECT MAX(p.id) FROM PostEntity p";
        TypedQuery<Long> query1 = entityManager.createQuery(sql1, Long.class);
        Long maxId = query1.getSingleResult();

        String sql2 = "SELECT MIN(p.id) FROM PostEntity p";
        TypedQuery<Long> query2 = entityManager.createQuery(sql2, Long.class);
        Long minId = query2.getSingleResult();

        String sql = "SELECT p FROM PostEntity p " +
                "WHERE p.id > :minId AND p.id <= :maxId ORDER BY p.id ASC";

        TypedQuery<PostEntity> query = entityManager.createQuery(sql, PostEntity.class);

        if (idPost > maxId) {
            query.setParameter("minId", maxId - 2);
            query.setParameter("maxId", maxId);
        } else if (idPost >= minId) {
            query.setParameter("minId", idPost - 3);
            query.setParameter("maxId", idPost - 1);
        }
        query.setMaxResults(2);
        List<PostEntity> posts = query.getResultList();
        List<PostResponse> postsRp = new ArrayList<>();
        for (PostEntity post : posts) {
            postsRp.add(PostResponse.builder().id(post.getId()).user(post.getUser()).imgPosts(post.getImgPosts()).comments(post.getComments()).text(post.getText()).likedUsers(getUserLiked(post)).build());
        }
        return new HashSet<>(postsRp);
    }
}


