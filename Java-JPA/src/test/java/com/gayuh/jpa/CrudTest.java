package com.gayuh.jpa;

import com.gayuh.jpa.entity.Customer;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CrudTest {

    private EntityManagerFactory managerFactory;

    @BeforeEach
    void setUp(){
        managerFactory = JpaUtil.getFACTORY();
    }

    @Test
    void insert() {
        EntityManager entityManager = managerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();;
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setName("Gayuh");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void read() {
        EntityManager entityManager = managerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();;
        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class, "86af7e73-0693-45e9-8af2-5573ee45d8b4");
        Assertions.assertEquals(customer.getName(), "Gayuh");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void update() {
        EntityManager entityManager = managerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();;
        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class, "86af7e73-0693-45e9-8af2-5573ee45d8b4");
        customer.setName("Ahmad Solikhin Gayuh Raharjo");
        entityManager.merge(customer);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void delete() {
        EntityManager entityManager = managerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();;
        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class, "86af7e73-0693-45e9-8af2-5573ee45d8b4");

        entityManager.remove(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
