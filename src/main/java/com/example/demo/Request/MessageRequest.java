package com.example.demo.Request;

import com.example.demo.Entity.RoomEntity;
import com.example.demo.Response.IEmpty;
import com.example.demo.Response.UserResponse;
import lombok.Data;

import java.sql.Date;

@Data
public class MessageRequest implements IEmpty {
    private String _id;
    private UserResponse user;
    private RoomEntity room;
    private String text;
    private Date createdAt;
    private static int type=1;

}
