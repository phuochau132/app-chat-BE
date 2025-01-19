package com.example.demo.Api;

import com.example.demo.Entity.StoryEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repositories.RoomRepository;
import com.example.demo.Request.StoryRequest;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
import com.example.demo.Response.UserResponse;
import com.example.demo.Service.UserService;
import com.example.demo.UploadFile.UploadFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    UploadFile uploadFile;
    @Autowired
    RoomRepository roomRepository;
    private final ObjectMapper objectMapper;

    @GetMapping()
    public ResponseEntity<Collection<UserEntity>> getAllUser() {
        try {
            return ResponseEntity.ok(userService.getAllUser());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(null);
        }
    }

    @PostMapping(value = "/profile")
    public ResponseEntity<IEmpty> editProfile( @RequestParam(value = "user", required = false) String user) {
        try {
            UserEntity user1 = objectMapper.readValue(user, UserEntity.class);
            UserResponse userR = userService.changeProfile(user1);
            return ResponseEntity.ok(userR);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("error").build());
        }
    }

    @PostMapping()
    public ResponseEntity<IEmpty> getInfoUserFToken(@RequestBody Map<String, String> requestBody) {
        System.out.println(requestBody.get("token"));
        try {
            return ResponseEntity.ok(userService.getInfoUser(requestBody.get("token")).get());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("error").build());
        }
    }
    @PostMapping(value = "/story")
    public ResponseEntity<Optional<StoryEntity>> addStory(@RequestBody Map<String,Object> requestBody) {
        String image = (String) requestBody.get("image");
        int userId = ((Number) requestBody.get("userId")).intValue();
        String content = (String) requestBody.get("content");
        int status = ((Number) requestBody.get("status")).intValue();
        try {
            return ResponseEntity.ok(userService.addStory(StoryRequest.builder().status(status).image(image).userId(userId).content(content).build()));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(null);
        }
    }

}