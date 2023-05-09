package com.gayuh.jpa;

import com.gayuh.jpa.entity.Brand;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ManageEntityTest {

    @Test
    void manageEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId(UUID.randomUUID().toString());
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        brand.setName("Apple");
        entityManager.persist(brand); //bran become manage entity

        entityManager.detach(brand); //brand become unmanaged entity now

        brand.setUpdatedAt(LocalDateTime.now());
        brand.setName("Apple International");

        entityTransaction.commit();
        entityManager.close();
    }
}
