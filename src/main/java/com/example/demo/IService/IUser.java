package com.example.demo.IService;

import com.example.demo.Entity.*;
import com.example.demo.Request.StoryRequest;
import com.example.demo.Response.FriendShipResponse;
import com.example.demo.Response.UserResponse;

import java.util.Collection;
import java.util.Optional;

public interface IUser {
    UserEntity saveUser(UserEntity user);
    UserEntity findByEmail(String email);
    void changePassword(String email, String newPassword);
    RoleEntity saveRole(RoleEntity roleEntity);
    void addToUser(String username,String rolename);
    Collection<UserEntity> getAllUser();
    UserResponse changeProfile(UserEntity userEntity);
    MessageEntity saveMessage(MessageEntity messageEntity);
    public Optional<UserEntity> findById(long id) ;
    public Optional<UserEntity> getInfoUser(String token);
    public Optional<StoryEntity> addStory(StoryRequest storyRequest);
}
