package com.example.demo.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse implements IEmpty {
    private Long id;
    private String nickName;
    private String birthDay;
    private String name;
    private String fullName;
    private String expoPushToken;
    private String story;
    private String avatar;
}
