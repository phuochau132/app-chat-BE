package com.example.demo.Response;

import com.example.demo.Entity.RoomEntity;
import com.example.demo.Entity.UserEntity;
import lombok.Data;

import java.sql.Date;

@Data
public class MessageResponse implements IErr{
    private Long id;
    private UserEntity sender;
    private UserEntity receiver;
    private RoomEntity room;
    private String content;
    private Date sentAt;
    private int type;
}

