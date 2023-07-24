package com.example.demo.ApiSokket;


import com.example.demo.Request.MessageRequest;
import com.example.demo.Response.ErrorResponse;
import com.example.demo.Response.IErr;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatApi {
    @SendTo("/topic/rooms")
    @MessageMapping("/chat")
    public ResponseEntity<IErr> greeting(@Payload MessageRequest message) {
        System.out.println(message);
        try {
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(ErrorResponse.builder().err("lỗi").build());
        }
    }
}