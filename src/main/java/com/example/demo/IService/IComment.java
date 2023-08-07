package com.example.demo.IService;

import com.example.demo.Entity.CommentEntity;
import com.example.demo.Entity.PostEntity;
import com.example.demo.Request.CommentRequest;
import com.example.demo.Request.PostRequest;

import java.util.Collection;

public interface IComment {
    public PostEntity addComment(CommentRequest comment);
    public Collection<CommentEntity> getComments(long postId);
}
