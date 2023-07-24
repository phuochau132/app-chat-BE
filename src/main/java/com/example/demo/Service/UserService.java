package com.example.demo.Service;

import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.IService.IUser;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
public class UserService implements IUser {
    @Autowired
    UserRepository userRepository;
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
        UserEntity userEntity=userRepository.findByEmail(username).get();
        RoleEntity roleEntity=roleRepository.findByName((rolename));
        userEntity.getRoles().add(roleEntity);
    }

    @Override
    public Collection<UserEntity> getAllUser() {
        return userRepository.getAllBy();
    }
}
