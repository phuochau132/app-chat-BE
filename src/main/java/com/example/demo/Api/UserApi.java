package com.example.demo.Api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Entity.FriendshipEntity;
import com.example.demo.Entity.MessageEntity;
import com.example.demo.Entity.RoomEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repositories.RoomRepository;
import com.example.demo.Request.FriendRequest;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
import com.example.demo.Service.UserService;
import com.example.demo.UploadFile.UploadFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
                String newAvatar = uploadFile.uploadFile(file,"user");
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

    @PostMapping(value = "/friend")
    @Transactional
    public ResponseEntity<IEmpty> addFriend(@RequestBody(required = false) FriendRequest friendRequest) {
        RoomEntity room=roomRepository.save(new RoomEntity("chat"));
        System.out.println(friendRequest.getUserReceive());
        System.out.println(friendRequest.getUserSend());
        FriendshipEntity friendshipEntity=new FriendshipEntity();
        friendshipEntity.setUser(friendRequest.getUserSend());
        friendshipEntity.setFriend(friendRequest.getUserReceive());
        friendshipEntity.setRoom(room);
        try {
            FriendshipEntity f1= userService.saveFriendShip(friendshipEntity);
            UserEntity user= userService.findById(friendshipEntity.getFriend().getId()).get();
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("error").build());
        }
    }
    @GetMapping(value = "/friend/{id}")
    @Transactional
    public ResponseEntity<Collection<FriendshipEntity>> getAllFriend(@PathVariable Long id) {
        try {
            Collection<FriendshipEntity> f1= userService.findAllFriend(id);
            return ResponseEntity.ok(f1);
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
    @PostMapping(value = "/message")
    public ResponseEntity<IEmpty> addMessage(@RequestBody MessageEntity messageEntity) {
        try {
            MessageEntity f1= userService.saveMessage(messageEntity);
            return ResponseEntity.ok(EmptyResponse.builder().message("thành công").build());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("error").build());
        }
    }
    @PostMapping(value = "/friend/{action}")
    public ResponseEntity<IEmpty> acceptRequestAddFriend(@PathVariable String action,@RequestBody Map<String, Long> requestBody) {
        FriendshipEntity f1=null;
        try {
            if(action.equals("accept")){
                f1= userService.acceptRequestAddFriend(requestBody.get("id"));
            }
            if(action.equals("delete")){
                f1=userService.delRequestAddFriend(requestBody.get("id"));
            }
            return ResponseEntity.ok(f1);
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