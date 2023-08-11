package com.example.demo.Api;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Repositories.RoomRepository;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
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
    public ResponseEntity<IEmpty> editProfile(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "user", required = false) String user) {
        try {
            UserEntity user1 = objectMapper.readValue(user, UserEntity.class);
            if (file != null) {
                String newAvatar = uploadFile.uploadFile(file, "user");
                user1.setAvatar(newAvatar);
                System.out.println(user1);
            }
            UserEntity userEntity = userService.changeProfile(user1);
            return ResponseEntity.ok(userEntity);
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


}