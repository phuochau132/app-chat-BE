package com.example.demo.IService;

import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Request.MessageRequest;
import com.example.demo.Response.MessageResponse;

import java.util.List;

public interface IMessage {
    List<MessageResponse> getMessage(int idRoom);
    boolean addMessage(MessageRequest message);

}
