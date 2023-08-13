package com.example.demo.Entity;

import com.example.demo.Response.IEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name = "message")
public class MessageEntity implements IEmpty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnoreProperties({"posts", "roles",})
    private UserEntity sender;
    @ManyToOne
    @JsonIgnoreProperties({"posts", "roles",})
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @JsonIgnore
    private RoomEntity room;
    private String text;
    private int type;
    private Date createAt;

    @PrePersist
    public void prePersist() {
        if (createAt == null) {
            createAt = new Date();
        }
    }

}