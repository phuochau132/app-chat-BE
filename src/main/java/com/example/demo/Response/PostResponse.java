package com.example.demo.Response;
import com.example.demo.Entity.CommentEntity;
import com.example.demo.Entity.ImgPostEntity;
import com.example.demo.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class PostResponse implements IEmpty {
    private Long id;
    private UserEntity user;
    private Collection<ImgPostEntity> imgPosts;
    private Collection<CommentEntity> comments;
    private String text;
    private int status;
    private Set<UserEntity> likedUsers;

}
