package com.example.demo.IService;

import com.example.demo.Entity.MessageEntity;
import com.example.demo.Entity.RoomEntity;
import com.example.demo.Request.MessageRequest;

public interface IRoom {
    RoomEntity getRoom(long idRoom);
    MessageEntity addMessage(MessageRequest message);

}
