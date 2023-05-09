package com.gayuh.jpa;

import com.gayuh.jpa.entity.Image;
import com.gayuh.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LobTest {

    @Test
    void lob() throws IOException, URISyntaxException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getFACTORY();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Image image = new Image();
        image.setName("Contoh");
        image.setDescription("contoh upload image");

        URL res = getClass().getResource("/images/contoh.jpg");
        File file = Paths.get(res.toURI()).toFile();
        System.out.println(file.getAbsolutePath());

        System.out.println();

        File file1 = new File(URLDecoder.decode(getClass().getResource("/images/contoh.jpg").getPath(), "UTF-8"));
        System.out.println(file1.getAbsolutePath());

        byte[] bytes = Files.readAllBytes(Path.of(res.toURI()));
        byte[] bytes2 = Files.readAllBytes(Path.of(file1.getAbsolutePath()));

        image.setImage(bytes);
        entityManager.persist(image);

        entityTransaction.commit();
        entityManager.close();
    }
}
