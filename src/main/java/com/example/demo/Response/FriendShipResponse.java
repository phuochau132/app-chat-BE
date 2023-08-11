package com.example.demo.Response;

import com.example.demo.Entity.RoomEntity;
import com.example.demo.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendShipResponse implements IEmpty {
    private Long id;
    private UserEntity user;
    private RoomEntity room;
    private Date createAt;
    private int status;

}

