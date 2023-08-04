package com.example.demo.IService;

import com.example.demo.Entity.*;
import com.example.demo.Request.PostRequest;

import java.util.Collection;
import java.util.Optional;

public interface IPost {
    public PostEntity addPost(PostRequest post);

}
