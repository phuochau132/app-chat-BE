package com.example.demo.IService;

import com.example.demo.Entity.CommentEntity;
import com.example.demo.Entity.PostEntity;
import com.example.demo.Request.PostRequest;

import java.util.Collection;

public interface IPost {
    public PostEntity addPost(PostRequest post);
    public PostEntity likePost(long idPost, long idUser);
    public PostEntity dislikePost(long idPost, long idUser);
    public Collection<PostEntity> getPosts(long idPost);

}
