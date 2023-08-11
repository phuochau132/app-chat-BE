package com.example.demo.IService;

import com.example.demo.Entity.RoomEntity;
import com.example.demo.Request.MessageRequest;

public interface IRoom {
    RoomEntity getRoom(long idRoom);
    RoomEntity addMessage(MessageRequest message);

}
