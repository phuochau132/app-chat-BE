package com.example.demo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    @JsonIgnoreProperties("imgPosts")
    private PostEntity post;
    private String urlImg;

    public ImgPostEntity(PostEntity post, String urlImg) {
        this.post = post;
        this.urlImg = urlImg;
    }
}