package com.example.demo.Entity;

import com.example.demo.Response.IEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

@Data
@Entity
@Table(name = "post")
public class PostEntity implements IEmpty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ImgPostEntity> imgPosts = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<CommentEntity> comments = new ArrayList<>();
    private String text;
    private int status;
    private Date createAt;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post_like", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> likedUsers = new HashSet<UserEntity>();
    @PrePersist
    public void prePersist() {
        if (createAt == null) {
            createAt = new Date();
        }
    }

}