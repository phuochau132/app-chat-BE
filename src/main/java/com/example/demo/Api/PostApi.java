package com.example.demo.Api;

import com.example.demo.Entity.PostEntity;
import com.example.demo.Repositories.ImagePostRepository;
import com.example.demo.Request.PostRequest;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
import com.example.demo.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")

@RequiredArgsConstructor
public class PostApi {
    @Autowired
    PostService postService;
    @Autowired
    ImagePostRepository imagePostRepository;

    @PostMapping()
    public ResponseEntity<IEmpty> addPosts(@RequestPart(value = "files", required = false) List<MultipartFile> files,
                                           @RequestParam(value = "idUser", required = false) long idUser,
                                           @RequestParam(value = "text", required = false) String text, @RequestParam(value = "status", required = false) int status) {
        try {
            PostEntity post = postService.addPost(PostRequest.builder().idUser(idUser).listFile(files).status(status).text(text).build());
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("Post failed").build());
        }
    }

    @PostMapping(value = "/like")
    public ResponseEntity<IEmpty> likePosts(@RequestBody Map<String, Long> requestBody) {
        long idPost = requestBody.get("idPost");
        long idUser = requestBody.get("idUser");
        System.out.println(idPost);
        System.out.println(idUser);
        try {
            PostEntity post = postService.likePost(idPost, idUser);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("Post failed").build());
        }
    }

    @PostMapping(value = "/dislike")
    public ResponseEntity<IEmpty> dislikePosts(@RequestBody Map<String, Long> requestBody) {
        long idPost = requestBody.get("idPost");
        long idUser = requestBody.get("idUser");
        try {
            PostEntity post = postService.dislikePost(idPost, idUser);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("Post failed").build());
        }
    }

    @GetMapping(value = "/{idPost}")
    public ResponseEntity<Collection<PostEntity>> getPosts(@PathVariable long idPost) {
        System.out.println(12391238);
        try {
            Collection<PostEntity> posts = postService.getPosts(idPost);
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).build();
        }
    }

}