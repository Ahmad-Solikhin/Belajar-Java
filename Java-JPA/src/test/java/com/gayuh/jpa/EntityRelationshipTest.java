package com.gayuh.jpa;

import com.gayuh.jpa.entity.*;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.UUID;

public class EntityRelationshipTest {

    @Test
    void oneToOne() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        String id = UUID.randomUUID().toString();

        Credential credential = new Credential();
        credential.setEmail("ahmadsgr39@gmail.com");
        credential.setId(id);
        credential.setPassword("Rahasia");
        entityManager.persist(credential);

        User user = new User();
        user.setId(id);
        user.setName("Gayuh");
        entityManager.persist(user);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "e7a530e2-44e3-458c-9dcc-3aa522fbb7da");
        Assertions.assertNotNull(user.getCredential());
        Assertions.assertNotNull(user.getWallet());

        Assertions.assertEquals("ahmadsgr39@gmail.com", user.getCredential().getEmail());
        Assertions.assertEquals(10_000_000, user.getWallet().getBalance().intValue());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneJoinColumn() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "e7a530e2-44e3-458c-9dcc-3aa522fbb7da");

        Wallet wallet = new Wallet();
        wallet.setBalance(10_000_000l);
        wallet.setUser(user);
        entityManager.persist(wallet);


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToManyInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setName("Samsung");
        entityManager.persist(brand);

        Product product1 = new Product();
        product1.setBrand(brand);
        product1.setName("Samsung Tab Galaxy A5");
        product1.setPrice(5_000_000L);
        entityManager.persist(product1);

        Product product2 = new Product();
        product2.setBrand(brand);
        product2.setName("Samsung Tab Galaxy Z FLip 4");
        product2.setPrice(15_000_000L);
        entityManager.persist(product2);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToManyFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "7d92b34b-7246-4eec-a86e-dd9edc839d29");
        Assertions.assertNotNull(brand.getProducts());

        Assertions.assertEquals(2, brand.getProducts().size());

        brand.getProducts().forEach(prod -> System.out.println(prod.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void manyToManyInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "e7a530e2-44e3-458c-9dcc-3aa522fbb7da");
        user.setLikes(new HashSet<>());

        Product product1 = entityManager.find(Product.class, "0e070236-6045-4d87-b4af-885f37d431b5");
        Product product2 = entityManager.find(Product.class, "4b542c74-3d66-4410-9f4c-110b3eccf89e");

        user.getLikes().add(product1);
        user.getLikes().add(product2);

        entityManager.merge(user);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void manyToManyFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "e7a530e2-44e3-458c-9dcc-3aa522fbb7da");
        user.getLikes().remove(entityManager.find(Product.class, "0e070236-6045-4d87-b4af-885f37d431b5"));

        entityTransaction.commit();
        user.getLikes().forEach(prod -> System.out.println(prod.getName()));
        entityManager.close();
    }

    @Test
    void fetch() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Product product = entityManager.find(Product.class, "0e070236-6045-4d87-b4af-885f37d431b5");

        System.out.println(product.getBrand().getName());

        entityTransaction.commit();
        entityManager.close();
    }

}
