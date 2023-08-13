package com.example.demo.IService;

import com.example.demo.Entity.PostEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Request.PostRequest;
import com.example.demo.Response.PostResponse;

import java.util.Collection;

public interface IPost {
    public PostEntity addPost(PostRequest post);
    public UserEntity likePost(long idPost, long idUser);
    public UserEntity dislikePost(long idPost, long idUser);
    public Collection<PostResponse> getPosts(long idPost);

}
