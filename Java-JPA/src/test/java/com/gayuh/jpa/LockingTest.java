package com.gayuh.jpa;

import com.gayuh.jpa.entity.Brand;
import com.gayuh.jpa.entity.Customer;
import com.gayuh.jpa.entity.CustomerType;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class LockingTest {

    @Test
    void versionTest() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        var brand = new Brand();
        brand.setId(UUID.randomUUID().toString());
        brand.setName("Nokia");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        brand.setDescription("Barang ini lebih keras dari batu");
        entityManager.persist(brand);

        System.out.println(brand.getVersion());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void OptimisticLockingDemo() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        var brand = entityManager.find(Brand.class, "f761c2c9-676a-44c6-bd5e-e9140fbc6944");
        brand.setName("All new Nokia");
        brand.setUpdatedAt(LocalDateTime.now());

        Thread.sleep(10 * 1_000);
        entityManager.merge(brand);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void OptimisticLockingDemoFinish() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        var brand = entityManager.find(Brand.class, "f761c2c9-676a-44c6-bd5e-e9140fbc6944");
        brand.setName("All new Nokia 2");
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.merge(brand);

        entityTransaction.commit();
        entityManager.close();
    }
}
