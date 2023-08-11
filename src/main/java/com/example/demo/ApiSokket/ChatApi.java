package com.example.demo.ApiSokket;


import com.example.demo.Request.SMessageRequest;
import com.example.demo.Request.UserRequest;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatApi {
    @SendTo("/topic/rooms/{roomId}")
    @MessageMapping("/chat/{roomId}")
    public ResponseEntity<IEmpty> getting(@Payload SMessageRequest messageRequest) {
        System.out.println(98123);
        System.out.println(messageRequest);
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
}