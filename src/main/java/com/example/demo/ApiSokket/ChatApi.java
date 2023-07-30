package com.example.demo.ApiSokket;


import com.example.demo.Request.MessageRequest;
import com.example.demo.Request.UserRequest;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
import com.example.demo.Entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class ChatApi {
    @SendTo("/topic/rooms")
    @MessageMapping("/chat")
    public ResponseEntity<IEmpty> greeting(@Payload MessageRequest message) {
        System.out.println(message);
        try {
            return ResponseEntity.ok(message);
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
}