package com.example.demo.Custom;

import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleCusTomResponse {
    @PersistenceContext
    private EntityManager entityManager;

    public List<RoleEntity> getRole(UserEntity user) {
        String sql = "SELECT r FROM RoleEntity r " +
                "JOIN r.users u " +
                "WHERE u.name = :name";

        TypedQuery<RoleEntity> query = entityManager.createQuery(sql, RoleEntity.class);
        query.setParameter("name", user.getName());

        return query.getResultList();
    }
}


