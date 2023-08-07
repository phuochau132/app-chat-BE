package com.example.demo.Service;


import com.example.demo.Entity.CommentEntity;
import com.example.demo.Entity.PostEntity;
import com.example.demo.IService.IComment;
import com.example.demo.Repositories.CommentRepository;
import com.example.demo.Repositories.PostRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
public class CommentService implements IComment {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public PostEntity addComment(CommentRequest comment) {
        PostEntity post=postRepository.findById(comment.getPostId()).get();
        CommentEntity cmt = new CommentEntity();
        cmt.setUser(userRepository.findById(comment.getUserId()).get());
        cmt.setPost(post);
        cmt.setText(comment.getText());
        if (comment.getParentCommentId() != -1) {
            cmt.setParentCommentId(commentRepository.findById(comment.getParentCommentId()).get());
        }
        post.getComments().add(cmt);
        return postRepository.save(post);
    }

    @Override
    public Collection<CommentEntity> getComments(long postId) {
        PostEntity post = postRepository.findById(postId).get();
        return commentRepository.findByPost(post);
    }
}
