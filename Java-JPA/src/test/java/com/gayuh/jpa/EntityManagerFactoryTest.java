package com.gayuh.jpa;

import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityManagerFactoryTest {

    @Test
    void create() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        Assertions.assertNotNull(entityManagerFactory);
    }
}
