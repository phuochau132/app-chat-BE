package com.example.demo.Request;

import com.example.demo.Response.IEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class SUserRequest implements IEmpty {
    private long _id;
    private String name;
    private String avatar;

}
