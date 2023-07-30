package com.example.demo.Service;

import com.example.demo.Custom.FriendShipCusTomResponse;
import com.example.demo.Entity.FriendshipEntity;
import com.example.demo.Entity.MessageEntity;
import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.IService.IUser;
import com.example.demo.Repositories.FriendShipRepository;
import com.example.demo.Repositories.MessageRepository;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;


@Service
public class UserService implements IUser {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendShipRepository friendShipRepository;
    @Autowired
    FriendShipCusTomResponse friendShipCusTomResponse;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserEntity saveUser(UserEntity user) {
        System.out.println(98123);
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public RoleEntity saveRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }
    @Transactional
    @Override
    public void addToUser(String username, String rolename) {
        UserEntity userEntity=userRepository.findByName(username).get();
        RoleEntity roleEntity=roleRepository.findByName((rolename));
        userEntity.getRoles().add(roleEntity);
    }

    @Override
    public Collection<UserEntity> getAllUser() {
        return userRepository.getAllBy();
    }

    @Override
    public UserEntity changeProfile(UserEntity userEntity) {
        Optional<UserEntity> optionalUser= userRepository.findById(userEntity.getId());
        UserEntity user = optionalUser.get();
        user.setAvatar(userEntity.getAvatar());
        user.setFullName(userEntity.getFullName());
        user.setNickName(userEntity.getNickName());
        user.setStory(userEntity.getStory());
        user.setBirthDay(userEntity.getBirthDay());
        userRepository.save(user);
        return user;
    }
    @Override
    public FriendshipEntity saveFriendShip(FriendshipEntity friendshipEntity) {
        return friendShipRepository.save(friendshipEntity);
    }
    @Override
    public MessageEntity saveMessage(MessageEntity messageEntity) {
        return messageRepository.save(messageEntity);
    }
    @Override
    public Optional<UserEntity> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Collection<FriendshipEntity> findAllFriend(long idUSer) {
        return friendShipCusTomResponse.getUserSendRequest(idUSer);
    }
    @Override
    public FriendshipEntity acceptRequestAddFriend(long idFs) {
        Optional<FriendshipEntity> f= friendShipRepository.findById(idFs);
        FriendshipEntity friend= f.get();
        friend.setStatus(1);
        return friendShipRepository.save(friend);
    }
    @Transactional
    @Override
    public FriendshipEntity delRequestAddFriend(long idFs) {
        Optional<FriendshipEntity> f= friendShipRepository.findById(idFs);
        friendShipRepository.delete(f.get());
        return f.get();

    }


}
