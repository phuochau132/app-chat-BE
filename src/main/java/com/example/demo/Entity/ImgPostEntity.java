package com.example.demo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @JsonIgnore
    private PostEntity post;
    private String urlImg;

    public ImgPostEntity(PostEntity post, String urlImg) {
        this.post = post;
        this.urlImg = urlImg;
    }
}