package com.example.demo.Service;

import com.example.demo.Entity.CommentEntity;
import com.example.demo.IService.IComment;
import com.example.demo.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService implements IComment {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public CommentEntity addComment(CommentEntity comment) {
        return null;
    }
}
