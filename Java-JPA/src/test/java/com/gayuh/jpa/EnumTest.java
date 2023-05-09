package com.gayuh.jpa;

import com.gayuh.jpa.entity.Customer;
import com.gayuh.jpa.entity.CustomerType;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class EnumTest {

    @Test
    void enumerated() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setName("Solikhin");
        customer.setMarried(false);
        customer.setAge(21);
        customer.setPrimaryEmail("somad39@gmail.com");
        customer.setType(CustomerType.VIP);

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
