package com.example.demo.Entity;

import com.example.demo.Response.IEmpty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "friendships")
public class FriendshipEntity implements IEmpty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private UserEntity friend;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;
    //0 chờ
    //1 bạn bè
    private Date createAt;
    private int status=0;
    @PrePersist
    public void prePersist() {
        if (createAt == null) {
            createAt = new Date();
        }
    }
}