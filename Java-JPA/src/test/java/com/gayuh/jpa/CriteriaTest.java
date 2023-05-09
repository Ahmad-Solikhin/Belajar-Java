package com.gayuh.jpa;

import com.gayuh.jpa.entity.Brand;
import com.gayuh.jpa.entity.Product;
import com.gayuh.jpa.entity.SimpleBrand;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CriteriaTest {

    @Test
    void criteriaQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaQuery<Brand> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Brand.class);
        Root<Brand> b = criteriaQuery.from(Brand.class);
        criteriaQuery.select(b);

        TypedQuery<Brand> query = entityManager.createQuery(criteriaQuery);
        List<Brand> brands = query.getResultList();

        brands.forEach(v -> {
            System.out.println(v.getId());
            System.out.println(v.getName());
            System.out.println(v.getCreatedAt());
        });

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaQueryNonEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(builder.array(b.get("id"), b.get("name")));

        TypedQuery<Object[]> query = entityManager.createQuery(criteria);
        List<Object[]> objects = query.getResultList();

        objects.forEach(v -> {
            System.out.println("Id : " + v[0]);
            System.out.println("Name : " + v[1]);
        });

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaQueryConstructor() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<SimpleBrand> criteriaQuery = builder.createQuery(SimpleBrand.class);
        Root<Brand> b = criteriaQuery.from(Brand.class);
        criteriaQuery.select(builder.construct(SimpleBrand.class, b.get("id"), b.get("name")));

        TypedQuery<SimpleBrand> query = entityManager.createQuery(criteriaQuery);
        List<SimpleBrand> simpleBrands = query.getResultList();
        simpleBrands.forEach(v ->{
            System.out.println("Id : " + v.getId());
            System.out.println("Name : " + v.getName());
        });

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriWhereClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(b);

        criteria.where(
          builder.equal(b.get("name"), "Xiaomi"),
          builder.isNotNull(b.get("createdAt"))
        );

        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        List<Brand> brands = query.getResultList();
        brands.forEach(v -> {
            System.out.println(v.getId());
            System.out.println(v.getName());
        });

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriWhereClauseUsingOr() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(b);

        criteria.where(
                builder.or(
                        builder.equal(b.get("name"), "Xiaomi"),
                        builder.equal(b.get("name"), "Samsung")
                ),
                builder.isNotNull(b.get("createdAt"))
        );

        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        List<Brand> brands = query.getResultList();
        brands.forEach(v -> {
            System.out.println(v.getId() + " : " + v.getName());
        });

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriJoinClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> p = criteria.from(Product.class);
        Join<Product, Brand> b = p.join("brand");

        criteria.select(p);
        criteria.where(
                builder.equal(b.get("name"), "Samsung")
        );

        TypedQuery<Product> query = entityManager.createQuery(criteria);
        List<Product> products = query.getResultList();
        products.forEach(v -> {
            System.out.println(v.getName() + " : " + v.getBrand().getName());
        });

        entityTransaction.commit();
        entityManager.close();
    }
}
