package com.example.demo.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Entity.MessageEntity;
import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.StoryEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.IService.IUser;
import com.example.demo.Repositories.MessageRepository;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.StoryRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Request.StoryRequest;
import com.example.demo.Response.UserResponse;
import com.example.demo.UploadFile.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @Autowired
    StoryRepository storyRepository;
    @Override
    public UserEntity saveUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAvatar("https://res.cloudinary.com/dvgjegefi/image/upload/v1736344909/20171206_01_ppq0sw.jpg");
        return userRepository.save(user);
    }
    @Override
    public void changePassword(String email, String newPassword) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }
    }
    @Override
    public UserEntity findByEmail(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }
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
        if (userEntity.getAvatar() != null) {
            user.setAvatar(userEntity.getAvatar());
        }
        user.setExpoPushToken(userEntity.getExpoPushToken());
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
        DecodedJWT decodedJWT = jwtService.verifyToken(token);
        String username = decodedJWT.getSubject();
        return userRepository.findByName(username);
    }

    @Override
    public Optional<StoryEntity> addStory(StoryRequest storyRequest) {
        Optional<UserEntity> user = userRepository.findById(storyRequest.getUserId());
        if (user.isPresent()) {
            StoryEntity storyEntity = new StoryEntity(storyRequest.getImage(), storyRequest.getContent(), user.get(), storyRequest.getStatus());
            StoryEntity savedStory = storyRepository.save(storyEntity);
            return Optional.of(savedStory);
        } else {
            return Optional.empty();
        }
}
}
