package com.example.demo.IService;

import com.example.demo.Entity.CommentEntity;
import com.example.demo.Entity.PostEntity;
import com.example.demo.Request.PostRequest;

public interface IComment {
    public CommentEntity addComment(CommentEntity comment);
}
