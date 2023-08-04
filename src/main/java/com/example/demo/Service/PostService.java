package com.example.demo.Service;

import com.example.demo.Entity.ImgPostEntity;
import com.example.demo.Entity.PostEntity;
import com.example.demo.IService.IPost;
import com.example.demo.Repositories.ImagePostRepository;
import com.example.demo.Repositories.PostRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Request.PostRequest;
import com.example.demo.UploadFile.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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

}
