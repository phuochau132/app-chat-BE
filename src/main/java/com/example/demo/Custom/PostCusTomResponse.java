package com.example.demo.Custom;

import com.example.demo.Entity.PostEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Response.PostResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    public Set<PostResponse> getPosts(long idPost) {
        String sql1 = "SELECT MAX(p.id) FROM PostEntity p";
        TypedQuery<Long> query1 = entityManager.createQuery(sql1, Long.class);
        Long maxId = query1.getSingleResult();

        if (maxId != null) {
            String sql2 = "SELECT MIN(p.id) FROM PostEntity p";
            TypedQuery<Long> query2 = entityManager.createQuery(sql2, Long.class);
            Long minId = query2.getSingleResult();

            // Truy vấn lấy các bài post theo thứ tự giảm dần
            String sql = "SELECT p FROM PostEntity p " +
                    "WHERE p.id < :idPost ORDER BY p.id DESC";

            TypedQuery<PostEntity> query = entityManager.createQuery(sql, PostEntity.class);
            query.setParameter("idPost", idPost);
            query.setMaxResults(2); // Giới hạn lấy 2 hàng

            List<PostEntity> posts = query.getResultList();
            List<PostResponse> postsRp = new ArrayList<>();

            for (PostEntity post : posts) {
                postsRp.add(PostResponse.builder()
                        .id(post.getId())
                        .createdAt(post.getCreateAt())
                        .user(post.getUser())
                        .imgPosts(post.getImgPosts())
                        .comments(post.getComments())
                        .text(post.getText())
                        .likedUsers(getUserLiked(post))
                        .build());
            }
            return new HashSet<>(postsRp);
        }
        return new HashSet<>();
    }


    public int delUserLiked(UserEntity user, long postId) {
        String sql = "DELETE FROM post_like WHERE user_id = :userId AND post_id = :postId";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId", user.getId());
        query.setParameter("postId", postId);
        int deletedCount = query.executeUpdate();
        return deletedCount;
    }

    @Transactional
    public Set<PostResponse> getPostsByUser(long idUser) {
        String sql = "SELECT p FROM PostEntity p join UserEntity as u on u.id=p.user.id where p.user.id=:id";
        TypedQuery<PostEntity> query = entityManager.createQuery(sql, PostEntity.class);
        query.setParameter("id", idUser);
        List<PostEntity> posts = query.getResultList();
        List<PostResponse> postsRp = new ArrayList<>();
        for (PostEntity post : posts) {
            postsRp.add(PostResponse.builder().id(post.getId()).user(post.getUser()).imgPosts(post.getImgPosts()).comments(post.getComments()).text(post.getText()).likedUsers(getUserLiked(post)).createdAt(post.getCreateAt()).build());
        }
        return new HashSet<>(postsRp);
    }


}


