package com.example.demo.Custom;

import com.example.demo.Entity.FriendshipEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class FriendShipCusTomResponse  {
    @PersistenceContext
    private EntityManager entityManager;

    public Collection<FriendshipEntity> getUserSendRequest(long id) {
        String jpql = "SELECT f as id FROM FriendshipEntity f WHERE ( f.friend.id = :userId) AND f.status = 0";
        TypedQuery<FriendshipEntity> query = entityManager.createQuery(jpql, FriendshipEntity.class);
        query.setParameter("userId", id);
        return query.getResultList();
    }
    public Collection<FriendshipEntity> getAllFriend(long id) {
        String jpql = "SELECT f as id FROM FriendshipEntity f WHERE ( f.friend.id = :userId) or  ( f.user.id = :userId)";
        TypedQuery<FriendshipEntity> query = entityManager.createQuery(jpql, FriendshipEntity.class);
        query.setParameter("userId", id);
        return query.getResultList();
    }
}


