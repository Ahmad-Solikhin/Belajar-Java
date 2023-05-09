package com.gayuh.jpa;

import com.gayuh.jpa.entity.*;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class EmbeddedTest {
    @Test
    void embedded() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Name name = new Name();
        name.setTitle("Mr");
        name.setFirstName("Ahmad");
        name.setMiddleName("Solikhin Gayuh");
        name.setLastName("Raharjo");

        Member member = new Member();
        member.setEmail("solmad39@gmail.com");
        member.setName(name);

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void embeddedId() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId id = new DepartmentId();
        id.setCompanyId(UUID.randomUUID().toString());
        id.setDepartmentId(UUID.randomUUID().toString());

        Department department = new Department();
        department.setId(id);
        department.setName("Gayuh Tech");

        entityManager.persist(department);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void findEmbeddedId() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId id = new DepartmentId();
        id.setCompanyId("acf83afb-f7e4-4351-9539-ac391c720b83");
        id.setDepartmentId("ca9c12ab-d32d-4e8c-9d8d-d5d05f32d0db");

        Department department = entityManager.find(Department.class, id);
        Assertions.assertEquals("Gayuh Tech", department.getName());

        entityTransaction.commit();
        entityManager.close();
    }
}
