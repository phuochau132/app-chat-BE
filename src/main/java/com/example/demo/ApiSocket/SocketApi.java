package com.example.demo.ApiSocket;


import com.example.demo.Request.SMessageRequest;
import com.example.demo.Request.StatusUserRequest;
import com.example.demo.Request.UserRequest;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
public class SocketApi {
    List<StatusUserRequest> users = new ArrayList<>();

    @SendTo("/topic/rooms/{roomId}")
    @MessageMapping("/chat/{roomId}")
    public ResponseEntity<IEmpty> getting(@Payload SMessageRequest messageRequest) {

        try {
            return ResponseEntity.ok(messageRequest);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("lỗi").build());
        }
    }

    @SendTo("/topic/notification/{id}")
    @MessageMapping("/notification/{id}")
    public ResponseEntity<IEmpty> addFriendNotification(@DestinationVariable("id") long id, @Payload UserRequest user) {
        System.out.println(user);
        try {
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("lỗi").build());
        }
    }

    @SendTo("/topic/users/status")
    @MessageMapping("/users/status")
    public List<StatusUserRequest> handleStatusUpdate(@Payload StatusUserRequest user) {
        user.setCreateAt(new Date());
        Iterator<StatusUserRequest> iterator = users.iterator();
        while (iterator.hasNext()) {
            StatusUserRequest item = iterator.next();
            if (item.getId() == user.getId()) {
                iterator.remove();
                break;
            }
        }
        users.add(user);
        System.out.println(789);
        System.out.println(users);

        return users;
    }


}