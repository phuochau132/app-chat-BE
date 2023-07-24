package com.example.demo.Service;

import com.example.demo.IService.IMessage;
import com.example.demo.Request.MessageRequest;
import com.example.demo.Response.MessageResponse;

import java.util.List;

public class MessageService implements IMessage {
    @Override
    public List<MessageResponse> getMessage(int idRoom) {
        return null;
    }

    @Override
    public boolean addMessage(MessageRequest message) {
        return false;
    }
}
