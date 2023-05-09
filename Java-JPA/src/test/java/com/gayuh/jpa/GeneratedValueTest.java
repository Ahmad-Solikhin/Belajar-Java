package com.gayuh.jpa;

import com.gayuh.jpa.entity.Category;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedValueTest {

    @Test
    void insert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Category category = new Category();
        category.setName("Elektronik");
        category.setDescription("""
                Barang elektronik untuk kebutuhan kelistrikan rumah anda
                """);
        entityManager.persist(category);

        Assertions.assertNotNull(category.getId());

        entityTransaction.commit();
        entityManager.close();
    }
}
