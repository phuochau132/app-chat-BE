package com.example.demo.IService;

import com.example.demo.Entity.FriendshipEntity;
import com.example.demo.Entity.MessageEntity;
import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.UserEntity;

import java.util.Collection;
import java.util.Optional;

public interface IUser {
    UserEntity saveUser(UserEntity user);
    RoleEntity saveRole(RoleEntity roleEntity);
    void addToUser(String username,String rolename);
    Collection<UserEntity> getAllUser();
    UserEntity changeProfile(UserEntity userEntity);
    FriendshipEntity saveFriendShip(FriendshipEntity friendshipEntity);
    MessageEntity saveMessage(MessageEntity messageEntity);
    public Optional<UserEntity> findById(long id) ;
    public Collection<FriendshipEntity> findAllFriend(long idUSer) ;
    public FriendshipEntity acceptRequestAddFriend(long idFS);
    public FriendshipEntity delRequestAddFriend(long idFS);
}
