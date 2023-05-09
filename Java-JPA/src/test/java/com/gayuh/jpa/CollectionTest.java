package com.gayuh.jpa;

import com.gayuh.jpa.entity.Customer;
import com.gayuh.jpa.entity.Member;
import com.gayuh.jpa.entity.Name;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CollectionTest {

    @Test
    void create() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        var name = new Name();
        name.setFirstName("Ahmad");
        name.setMiddleName("Solikhin Gayuh");
        name.setTitle("Mr");
        name.setLastName("Raharjo");

        var member = new Member();
        member.setName(name);
        member.setEmail("huwalahumba@gmail.com");
        member.setHobbies(List.of("Tidur", "Rebahan"));

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void update() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        var member = entityManager.find(Member.class, "e3a602a5-4d2d-4aa7-890c-239025fbe5a9");

        member.getHobbies().add("Ngoding");

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void updateSkills() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        var member = entityManager.find(Member.class, "e3a602a5-4d2d-4aa7-890c-239025fbe5a9");
        member.setSkills(new HashMap<>());
        member.getSkills().put("Java", 90);
        member.getSkills().put("PHP", 90);
        member.getSkills().put("Pyhton", 80);

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();
    }
}
