package com.example.demo.Request;

import com.example.demo.Entity.RoleEntity;
import com.example.demo.Response.IEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class UserRequest  implements IEmpty {
    private final String nickName=null;
    private final String birthDay=null;
    private final String name=null;
    private final String fullName=null;
    private final String story=null;
    private final String avatar="";
    private final Set<RoleEntity> roles=new HashSet<RoleEntity>();
    private final Set<UserRequest> friends = new HashSet<>();
}

