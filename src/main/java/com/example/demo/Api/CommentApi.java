package com.example.demo.Api;


import com.example.demo.Entity.PostEntity;
import com.example.demo.Request.CommentRequest;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
import com.example.demo.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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