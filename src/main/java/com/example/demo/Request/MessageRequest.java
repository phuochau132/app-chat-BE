package com.example.demo.Request;

import com.example.demo.Response.IEmpty;
import lombok.Data;

@Data
public class MessageRequest implements IEmpty {
    private String _id;
    private long receiverId;
    private long senderId;
    private long roomId;
    private String text;
    private static int type = 0;

}
