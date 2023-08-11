package com.example.demo.Repositories;

import com.example.demo.Entity.FriendshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendShipRepository extends JpaRepository<FriendshipEntity, Long> {
}