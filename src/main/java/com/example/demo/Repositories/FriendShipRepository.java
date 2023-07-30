package com.example.demo.Repositories;

import com.example.demo.Entity.FriendshipEntity;
import com.example.demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface FriendShipRepository extends JpaRepository<FriendshipEntity, Long> {
}