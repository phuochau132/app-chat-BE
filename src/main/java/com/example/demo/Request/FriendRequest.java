package com.example.demo.Request;

import com.example.demo.Entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class FriendRequest {
    private long senderId;
    private long receiverId;
}