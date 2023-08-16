package com.example.demo.Request;

import com.example.demo.Response.IEmpty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SMessageRequest implements IEmpty {
    private String _id;
    private SUserRequest user;
    private String text;
    private List<String> image;
    private Date createdAt;
    private static int type=1;
}
