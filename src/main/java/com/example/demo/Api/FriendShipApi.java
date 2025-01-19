package com.example.demo.Api;

import com.example.demo.Entity.FriendshipEntity;
import com.example.demo.Entity.RoomEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repositories.RoomRepository;
import com.example.demo.Request.FriendRequest;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.FriendShipResponse;
import com.example.demo.Response.IEmpty;
import com.example.demo.Service.FriendShipService;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendShipApi {
    @Autowired
    UserService userService;
    @Autowired
    FriendShipService friendShipService;
    @Autowired
    RoomRepository roomRepository;

    @PostMapping()
    @Transactional
    public ResponseEntity<IEmpty> addFriend(@RequestBody(required = false) FriendRequest friendRequest) {
        try {
            FriendShipResponse f1= friendShipService.saveFriendShip(friendRequest);
            return ResponseEntity.ok(f1);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("error").build());
        }
    }
    @GetMapping(value = "/{option}/{id}")
    public ResponseEntity<Collection<Object>> getRequestAddFriend(@PathVariable Long id, @PathVariable String option) {
        try {
            if (option.equals("getRequestAddFriend")) {
                Collection<FriendshipEntity> f1 = friendShipService.getRequestAddFriend(id);
                return ResponseEntity.ok(new ArrayList<>(f1));
            } else {
                Collection<FriendShipResponse> f1 = friendShipService.getAllFriend(id);
                return ResponseEntity.ok(new ArrayList<>(f1));
            }
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping(value = "/{action}")
    public ResponseEntity<IEmpty> acceptRequestAddFriend(@PathVariable String action,@RequestBody Map<String, Integer> requestBody) {
        FriendshipEntity f1=null;
        try {
            if(action.equals("accept")){
                f1= friendShipService.acceptRequestAddFriend(requestBody.get("id"));
            }
            if(action.equals("delete")){
                f1=friendShipService.delRequestAddFriend(requestBody.get("id"));
            }
            return ResponseEntity.ok(f1);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("error").build());
        }
    }
}