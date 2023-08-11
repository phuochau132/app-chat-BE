package com.example.demo.IService;

import com.example.demo.Entity.*;
import com.example.demo.Response.FriendShipResponse;

import java.util.Collection;
import java.util.Optional;

public interface IUser {
    UserEntity saveUser(UserEntity user);
    RoleEntity saveRole(RoleEntity roleEntity);
    void addToUser(String username,String rolename);
    Collection<UserEntity> getAllUser();
    UserEntity changeProfile(UserEntity userEntity);
    MessageEntity saveMessage(MessageEntity messageEntity);
    public Optional<UserEntity> findById(long id) ;
    public Optional<UserEntity> getInfoUser(String token);

}
