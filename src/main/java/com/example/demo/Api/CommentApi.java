package com.example.demo.Api;

import com.example.demo.Entity.CommentEntity;
import com.example.demo.Entity.PostEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repositories.ImagePostRepository;
import com.example.demo.Request.CommentRequest;
import com.example.demo.Request.PostRequest;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
import com.example.demo.Service.CommentService;
import com.example.demo.Service.PostService;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")

@RequiredArgsConstructor
public class CommentApi {
    @Autowired
    CommentService commentService;

    @PostMapping()
    public ResponseEntity<IEmpty> addComments(@RequestBody CommentRequest commentRequest) {
        System.out.println(commentRequest);
        try {
            PostEntity cmt = commentService.addComment(commentRequest);
            return ResponseEntity.ok(cmt);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("add comment failed").build());
        }
    }



}