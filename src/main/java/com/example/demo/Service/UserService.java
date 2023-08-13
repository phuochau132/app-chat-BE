package com.example.demo.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Entity.MessageEntity;
import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.IService.IUser;
import com.example.demo.Repositories.MessageRepository;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Response.UserResponse;
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
    MessageRepository messageRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;

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
        UserEntity userEntity = userRepository.findByName(username).get();
        RoleEntity roleEntity = roleRepository.findByName((rolename));
        userEntity.getRoles().add(roleEntity);
    }

    @Override
    public Collection<UserEntity> getAllUser() {
        return userRepository.getAllBy();
    }

    @Transactional
    @Override
    public UserResponse changeProfile(UserEntity userEntity) {
        Optional<UserEntity> optionalUser = userRepository.findById(userEntity.getId());
        UserEntity user = optionalUser.get();
        if (user.getAvatar() != null) {
            user.setAvatar(userEntity.getAvatar());
        }
        user.setFullName(userEntity.getFullName());
        user.setNickName(userEntity.getNickName());
        user.setStory(userEntity.getStory());
        user.setBirthDay(userEntity.getBirthDay());
        userRepository.save(user);
        return UserResponse.builder().id(user.getId()).nickName(user.getNickName()).fullName(user.getFullName()).avatar(user.getAvatar()).birthDay(user.getBirthDay()).expoPushToken(user.getExpoPushToken()).build();
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
    public Optional<UserEntity> getInfoUser(String token) {
        System.out.println(1235);
        System.out.println(token);
        DecodedJWT decodedJWT = jwtService.verifyToken(token);
        String username = decodedJWT.getSubject();

        System.out.println(decodedJWT);
        System.out.println(username);
        return userRepository.findByName(username);
    }
}
