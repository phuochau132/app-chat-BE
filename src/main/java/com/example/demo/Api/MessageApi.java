package com.example.demo.Api;

import com.example.demo.Entity.RoomEntity;
import com.example.demo.Request.MessageRequest;
import com.example.demo.Response.EmptyResponse;
import com.example.demo.Response.IEmpty;
import com.example.demo.Service.RoomService;
import com.example.demo.UploadFile.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageApi {
    @Autowired
    RoomService roomService;
    @Autowired
    UploadFile uploadFile;

    @PostMapping()
    public ResponseEntity<IEmpty> addMessageToRoom(@RequestBody MessageRequest messageRequest) {
        System.out.println(messageRequest);
        try {
            RoomEntity room= roomService.addMessage(messageRequest);
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(403).body(EmptyResponse.builder().message("error").build());
        }
    }




}