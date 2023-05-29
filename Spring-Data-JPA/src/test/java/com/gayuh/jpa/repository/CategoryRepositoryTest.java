package com.gayuh.jpa.repository;

import com.gayuh.jpa.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insertTest() {
        Category category = new Category();
        category.setName("Gadget");

        categoryRepository.save(category);

        Assertions.assertNotNull(category.getId());
    }

    @Test
    void updateTest() {
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        category.setName("GADGET");
        categoryRepository.save(category);

        category = categoryRepository.findById(1L).orElse(null);

        Assertions.assertEquals("GADGET", category.getName());
    }

    @Test
    void queryMethod() {
        Category category = categoryRepository.findFirstByNameEquals("GADGET").orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals("GADGET", category.getName());

        List<Category> categories = categoryRepository.findAllByNameLike("%Category%");
        Assertions.assertEquals(5, categories.size());
    }

    @Test
    void addAudit() {
        Category category = new Category();
        category.setName("Electronics");
        categoryRepository.save(category);

        Assertions.assertNotNull(category.getId());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
    }

    @Test
    void example1() {
        Category category = new Category();
        category.setName("Electronics");
        category.setId(22L);

        Example<Category> example = Example.of(category);

        List<Category> categories = categoryRepository.findAll(example);

        Assertions.assertEquals(1, categories.size());
    }

    @Test
    void exampleMatcher() {
        Category category = new Category();
        category.setName("electronics");

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
                .withIgnoreCase();

        Example<Category> example = Example.of(category, matcher);

        List<Category> categories = categoryRepository.findAll(example);

        Assertions.assertEquals(1, categories.size());
    }
}
