package com.example.demo.Service;

import com.example.demo.Entity.MessageEntity;
import com.example.demo.Entity.RoomEntity;
import com.example.demo.IService.IRoom;
import com.example.demo.Repositories.RoomRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Request.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomService implements IRoom {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserRepository userRepository;
    @Transactional
    @Override
    public MessageEntity addMessage(MessageRequest message) {
        RoomEntity room = roomRepository.findById(message.getRoomId()).orElseThrow();
        MessageEntity newMessage = new MessageEntity();
        newMessage.setReceiver(userRepository.findById(message.getReceiverId()).orElseThrow());
        newMessage.setSender(userRepository.findById(message.getSenderId()).orElseThrow());
        newMessage.setRoom(room);
        newMessage.setText(message.getText());
        room.getMessage().add(newMessage);
        return newMessage;
    }
    @Override
    public RoomEntity getRoom(long idRoom) {
        return roomRepository.findById(idRoom).get();
    }
}
