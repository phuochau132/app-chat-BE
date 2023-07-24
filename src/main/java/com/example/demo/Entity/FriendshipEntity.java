package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "friendships")
public class FriendshipEntity {
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

}