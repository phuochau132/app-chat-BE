package com.example.demo.Request;

import com.example.demo.Entity.RoomEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Response.IErr;
import com.example.demo.Response.UserResponse;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.sql.Date;

@Data
public class MessageRequest implements IErr {
    private String _id;
    private UserResponse user;
    private RoomEntity room;
    private String text;
    private Date createdAt;
    private static int type=1;

}
