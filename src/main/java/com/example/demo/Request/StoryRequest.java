package com.example.demo.Request;

import com.example.demo.Response.IEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StoryRequest implements IEmpty {
    private String image;
    private String content;
    private int userId;
    private int status;
}
