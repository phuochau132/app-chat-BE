package com.example.demo.Request;

import com.example.demo.Response.IEmpty;
import lombok.Data;

import java.util.List;

@Data
public class MessageRequest implements IEmpty {
    private String _id;
    private long receiverId;
    private long senderId;
    private long roomId;
    private String text;
    private List<String> images;
    private static int type = 0;

}
