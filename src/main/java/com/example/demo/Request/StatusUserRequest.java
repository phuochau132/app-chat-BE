package com.example.demo.Request;

import com.example.demo.Response.IEmpty;
import lombok.Data;

import java.util.Date;

@Data
public class StatusUserRequest implements IEmpty {
    private Long id;
    private String status;
    private Date createAt;

}

