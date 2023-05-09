package com.gayuh.jpa;

import com.gayuh.jpa.entity.Customer;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ColumnTest {
    @Test
    void column() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        var customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setName("Ahmad");
        customer.setPrimaryEmail("ahmadsgr39@gmail.com");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
