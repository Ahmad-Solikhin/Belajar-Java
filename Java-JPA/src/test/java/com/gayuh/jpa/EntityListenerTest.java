package com.gayuh.jpa;

import com.gayuh.jpa.entity.Category;
import com.gayuh.jpa.entity.Member;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityListenerTest {

    @Test
    void listener() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        var category = new Category();
        category.setName("Contoh");

        entityManager.persist(category);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void listenerPostLoad() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        var member = entityManager.find(Member.class, "e3a602a5-4d2d-4aa7-890c-239025fbe5a9");
        System.out.println(member.getFullName());

        entityTransaction.commit();
        entityManager.close();
    }
}
