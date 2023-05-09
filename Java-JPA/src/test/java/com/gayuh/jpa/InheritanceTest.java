package com.gayuh.jpa;

import com.gayuh.jpa.entity.*;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class InheritanceTest {

    @Test
    void singleTableInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Employee employee = new Employee();
        employee.setName("Raharjo");
        entityManager.persist(employee);

        Manager manager = new Manager();
        manager.setTotalEmployee(1);
        manager.setName("Ahmad");
        entityManager.persist(manager);

        VicePresident vp = new VicePresident();
        vp.setTotalManager(1);
        vp.setName("Gayuh");
        entityManager.persist(vp);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void singleTableFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Employee employee = entityManager.find(Employee.class, "be7b3885-ebee-44f7-bf07-ab23a72f0417");

        VicePresident vp = (VicePresident) employee;
        System.out.println(vp.getName());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PaymentGopay gopay = new PaymentGopay();
        gopay.setGopayId("gopay1");
        gopay.setAmount(10_000L);
        entityManager.persist(gopay);

        PaymentCreditCard creditCard = new PaymentCreditCard();
        creditCard.setMaskedCard("455-555");
        creditCard.setBank("BCA");
        creditCard.setAmount(100_000L);
        entityManager.persist(creditCard);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Payment payment = entityManager.find(Payment.class, "9e9c7afc-bf9b-4e13-a6f4-316c33634fb7");
        PaymentGopay gopay = (PaymentGopay) payment;
        System.out.println(gopay.getGopayId());

        PaymentCreditCard creditCard = entityManager.find(PaymentCreditCard.class, "4363bd53-9a03-4cb4-a296-04abddc978be");
        System.out.println(creditCard.getBank());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tablePerClassInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction = new Transaction();
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setBalance(1_000_000l);
        entityManager.persist(transaction);

        TransactionDebit debit = new TransactionDebit();
        debit.setCreatedAt(LocalDateTime.now());
        debit.setBalance(2_000_000l);
        debit.setDebitAmount(1_000_000l);
        entityManager.persist(debit);

        TransactionsCredit credit = new TransactionsCredit();
        credit.setCreatedAt(LocalDateTime.now());
        credit.setBalance(2_000_000l);
        credit.setCreditAmount(1_000_000l);
        entityManager.persist(credit);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tablePerClassFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TransactionsCredit credit = entityManager.find(TransactionsCredit.class, "3bbbcb74-a43e-46a4-b26e-168fb62a8541");
        Transaction transaction = entityManager.find(Transaction.class, "ff98f4a7-c3c2-42d2-a581-d84ced793e96");
        TransactionDebit credit1 = (TransactionDebit) transaction;

        Assertions.assertEquals(transaction.getBalance(), credit1.getBalance());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void mappedSuperClass() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId(UUID.randomUUID().toString());
        brand.setName("Xiaomi");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.persist(brand);

        entityTransaction.commit();
        entityManager.close();
    }
}
