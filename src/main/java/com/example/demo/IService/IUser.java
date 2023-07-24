package com.example.demo.IService;

import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.UserEntity;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public interface IUser {
    UserEntity saveUser(UserEntity user);
    RoleEntity saveRole(RoleEntity roleEntity);
    void addToUser(String username,String rolename);
    Collection<UserEntity> getAllUser();
}
