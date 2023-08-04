package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "imagepost")
@NoArgsConstructor
@Data
public class ImgPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_post", referencedColumnName = "id")
    @JsonIgnoreProperties("imgPosts") // Ignore serialization of the imgPosts field in PostEntity
    private PostEntity post;
    private String urlImg;

    public ImgPostEntity(PostEntity post, String urlImg) {
        this.post = post;
        this.urlImg = urlImg;
    }
}