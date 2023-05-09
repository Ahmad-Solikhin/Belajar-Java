package com.gayuh.jpa;

import com.gayuh.jpa.entity.Brand;
import com.gayuh.jpa.entity.Member;
import com.gayuh.jpa.entity.Product;
import com.gayuh.jpa.entity.SimpleBrand;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class JpaQueryLanguageTest {

    @Test
    void selectQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b", Brand.class);
        List<Brand> brands = query.getResultList();

        brands.forEach(obj -> System.out.println(obj.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void selectWhereQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("select m from Member m " +
                "where m.name.firstName = :firstName", Member.class);
        query.setParameter("firstName", "Ahmad");

        List<Member> members = query.getResultList();
        members.forEach(obj -> System.out.println(obj.getId() + " " + obj.getFullName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinClauseQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join fetch p.brand b order by p.name desc", Product.class);

        //Mulai dari
        query.setFirstResult(0);

        //Limit
        query.setMaxResults(1);

        List<Product> products = query.getResultList();

        products.forEach(obj -> {
            System.out.println("Product Name : " + obj.getName());
            System.out.println("Brand Name : " + obj.getBrand().getName());
        });

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void selectSomeFileds() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, b.name from Brand b where b.name = :name", Object[].class);
        query.setParameter("name", "Samsung");

        List<Object[]> objects = query.getResultList();
        objects.forEach(v -> System.out.println(v[0] + ": " + v[1]));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void selectNewConstructor() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<SimpleBrand> query = entityManager.createQuery("select new com.gayuh.jpa.entity.SimpleBrand(b.id, b.name) from Brand b " +
                "where b.name = :name", SimpleBrand.class);
        query.setParameter("name", "Samsung");

        List<SimpleBrand> brands = query.getResultList();
        brands.forEach(v -> System.out.println(v.getId() + ": " + v.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void aggregateQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select min(p.price), max(p.price), avg(p.price) from Product p",
                Object[].class);
        Object[] result = query.getSingleResult();

        System.out.println("Min : " + result[0]);
        System.out.println("Max : " + result[1]);
        System.out.println("Avg : " + result[2]);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void aggregateQueryGroupBy() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, b.name, min(p.price), max(p.price) " +
                        "from Product p join p.brand b group by b.id having min(p.price) > :price", Object[].class);
        query.setParameter("price", 500_000l);
        List<Object[]> result = query.getResultList();

        result.forEach(v -> {
            System.out.println("Id : " + v[0]);
            System.out.println("Brand : " + v[1]);
            System.out.println("Min : " + v[2]);
            System.out.println("Max : " + v[2]);
        });

        entityTransaction.commit();
        entityManager.close();
    }
    
    @Test
    void nativeQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createNativeQuery("select * from brands where created_at is not null", Brand.class);
        List<Brand> brands = query.getResultList();

        brands.forEach(v -> System.out.println(v.getName()));

        entityTransaction.commit();
        entityManager.close();
    }
}
