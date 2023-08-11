package com.example.demo.Entity;

import com.example.demo.Response.IEmpty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
@Entity
@Table(name = "room")
@Data
@RequiredArgsConstructor
public class RoomEntity implements IEmpty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<MessageEntity> message = new ArrayList<>();
    public RoomEntity(String name) {
        this.name = name;
    }
}