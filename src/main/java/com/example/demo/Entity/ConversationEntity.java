//package com.example.demo.Entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "conversation")
//public class ConversationEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity userThis;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity useThat;
//
//}