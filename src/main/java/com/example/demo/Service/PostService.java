package com.example.demo.Service;

import com.example.demo.Custom.PostCusTomResponse;
import com.example.demo.Entity.ImgPostEntity;
import com.example.demo.Entity.PostEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.IService.IPost;
import com.example.demo.Repositories.CommentRepository;
import com.example.demo.Repositories.ImagePostRepository;
import com.example.demo.Repositories.PostRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Request.PostRequest;
import com.example.demo.Response.PostResponse;
import com.example.demo.UploadFile.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
public class PostService implements IPost {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImagePostRepository imagePostRepository;
    @Autowired
    UploadFile uploadFile;
    @Autowired
    PostCusTomResponse postCusTomResponse;
    @Autowired
    CommentRepository commentRepository;

    @Override
    public PostEntity addPost(PostRequest postRequest) {
        PostEntity post = new PostEntity();
        post.setUser(userRepository.findById(postRequest.getIdUser()).orElse(null));
        post.setText(postRequest.getText());
        post.setStatus(postRequest.getStatus());
        postRepository.save(post);
        List<ImgPostEntity> images = new ArrayList<>();
        if (postRequest.getListFile() != null) {
            postRequest.getListFile().forEach(file -> {
                String url = uploadFile.uploadFile(file, "post");
                ImgPostEntity imgPostEntity = new ImgPostEntity(post, url);
                imagePostRepository.save(imgPostEntity);
                images.add(imgPostEntity);
            });
        }
        post.setImgPosts(images);
        return post;
    }

    @Transactional
    @Override
    public PostResponse likePost(long idPost, long idUser) {
        PostEntity post = postRepository.findById(idPost).get();
        UserEntity user = userRepository.findById(idUser).get();
        post.getLikedUsers().add(user);
        postRepository.save(post);
        return PostResponse.builder().id(post.getId()).user(post.getUser()).imgPosts(post.getImgPosts()).comments(post.getComments()).text(post.getText()).likedUsers(postCusTomResponse.getUserLiked(post)).build();
    }

    @Transactional
    @Override
    public PostResponse dislikePost(long idPost, long idUser) {
        PostEntity post = postRepository.findById(idPost).get();
        UserEntity user = userRepository.findById(idUser).get();
        post.getLikedUsers().remove(user);
        postRepository.save(post);
        Set<UserEntity> users = postCusTomResponse.getUserLiked(post);
        users.remove(user);
        return PostResponse.builder().id(post.getId()).user(post.getUser()).imgPosts(post.getImgPosts()).comments(post.getComments()).text(post.getText()).likedUsers(users).build();
    }

    @Override
    public Collection<PostResponse> getPosts(long idPost) {
        return postCusTomResponse.getPosts(idPost);
    }

}
