package com.example.demo.Entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;

import java.sql.Date;

@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    private String content;
    private int type;
    private Date sentAt;

}