package com.gayuh.jpa.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    private static EntityManagerFactory FACTORY = null;

    public static EntityManagerFactory getFACTORY() {
        if (FACTORY == null){
            FACTORY = Persistence.createEntityManagerFactory("BELAJAR");
        }
        return FACTORY;
    }
}
