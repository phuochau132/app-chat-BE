package com.example.demo.Response;

import com.example.demo.Entity.ImgPostEntity;
import com.example.demo.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
public class PostResponse implements IEmpty {
    private Long id;
    private UserEntity user;
    private Collection<ImgPostEntity> imgPosts ;
    private String text;
    private Date createAt;
}
