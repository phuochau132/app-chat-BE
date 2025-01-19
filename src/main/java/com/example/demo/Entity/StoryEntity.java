package com.example.demo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "story")
@NoArgsConstructor
@Data
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private UserEntity user;
    private String urlImg;
    private int status = 0;
    private String content;
    private Date createAt;
    public StoryEntity(String image, String content, UserEntity user, Integer status) {
        this.urlImg=image;
        this.content=content;
        this.user = user;
        this.status = status;
    }

    @PrePersist
    public void prePersist() {
        if (createAt == null) {
            createAt = new Date();
        }
    }
}