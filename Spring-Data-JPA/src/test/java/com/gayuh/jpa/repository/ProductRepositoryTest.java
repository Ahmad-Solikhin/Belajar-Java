package com.gayuh.jpa.repository;

import com.gayuh.jpa.entity.Category;
import com.gayuh.jpa.entity.Product;
import com.gayuh.jpa.model.ProductPrice;
import com.gayuh.jpa.model.SimpleProduct;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.support.TransactionOperations;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Test
    void createProducts() {
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        Product product = new Product();
        product.setName("Samsung Galaxy S40");
        product.setCategory(category);
        product.setPrice(10_000_000L);
        productRepository.save(product);

        product = new Product();
        product.setName("Poco F51");
        product.setCategory(category);
        product.setPrice(5_000_000L);
        productRepository.save(product);

    }

    @Test
    void findProduct() {
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        List<Product> products = productRepository.findAllByCategory_Name(category.getName());
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Samsung Galaxy S40", products.get(0).getName());
        Assertions.assertEquals("Poco F51", products.get(1).getName());
    }

    @Test
    void findProductSort() {
        Sort sort = Sort.by(Sort.Order.desc("id"));

        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        List<Product> products = productRepository.findAllByCategory_Name(category.getName(), sort);
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Samsung Galaxy S40", products.get(1).getName());
        Assertions.assertEquals("Poco F51", products.get(0).getName());
    }

    @Test
    void pageable() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.findAllByCategory_Name("GADGET", pageable);
        Assertions.assertEquals(1, products.getContent().size()); //Jumlah konten dalam halaman
        Assertions.assertEquals(0, products.getNumber()); //Page saat ini
        Assertions.assertEquals(2, products.getTotalElements()); //Jumlah data
        Assertions.assertEquals(2, products.getTotalPages()); //Jumlah halaman
        Assertions.assertEquals("Poco F51", products.getContent().get(0).getName());

        pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")));
        products = productRepository.findAllByCategory_Name("GADGET", pageable);
        Assertions.assertEquals(1, products.getContent().size()); //Jumlah konten dalam halaman
        Assertions.assertEquals(1, products.getNumber()); //Page saat ini
        Assertions.assertEquals(2, products.getTotalElements()); //Jumlah data
        Assertions.assertEquals(2, products.getTotalPages()); //Jumlah halaman
        Assertions.assertEquals("Samsung Galaxy S40", products.getContent().get(0).getName());
    }

    @Test
    void count() {
        Long countAll = productRepository.count();
        Assertions.assertEquals(2, countAll);

        Long countByValid = productRepository.countByCategory_Name("GADGET");
        Assertions.assertEquals(2, countByValid);

        Long countByInvalid = productRepository.countByCategory_Name("Pisau");
        Assertions.assertEquals(0, countByInvalid);
    }

    @Test
    void exist() {
        Boolean existTure = productRepository.existsByNameLike("%Samsung%");
        Assertions.assertTrue(existTure);

        Boolean existFalse = productRepository.existsByNameLike("%Xiaomi%");
        Assertions.assertFalse(existFalse);
    }

    @Test
    void deleteOld() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);
            Assertions.assertNotNull(category);

            Product product = new Product();
            product.setPrice(5_000_000L);
            product.setName("Nukie");
            product.setCategory(category);
            productRepository.save(product); //Transaksi 1

            Integer sumOfDelete = productRepository.deleteByName("Nukie"); //Transaksi 1
            Assertions.assertEquals(1, sumOfDelete);

            Boolean exist = productRepository.existsByNameLike("%Nulie%");
            Assertions.assertFalse(exist);
        });
    }

    @Test
    void deleteNew() {
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        Product product = new Product();
        product.setPrice(5_000_000L);
        product.setName("Nukie");
        product.setCategory(category);
        productRepository.save(product); //Ini transaksi 1

        Integer sumOfDelete = productRepository.deleteByName("Nukie");
        Assertions.assertEquals(1, sumOfDelete); //Ini transaksi 2

        Boolean exist = productRepository.existsByNameLike("%Nulie%");
        Assertions.assertFalse(exist);
    }

    @Test
    void testNamedQuery() {
        Pageable pageable = PageRequest.of(0, 1);

        List<Product> products = productRepository.searchProductUsingName("Samsung Galaxy S40", pageable);

        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals("Samsung Galaxy S40", products.get(0).getName());
    }

    @Test
    void query() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));

        Page<Product> products = productRepository.searchProduct("%Poco%", pageable);
        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(1, products.getTotalPages());
        Assertions.assertEquals(1, products.getTotalElements());

        products = productRepository.searchProduct("%GADGET%", pageable);
        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals(2, products.getTotalElements());
    }

    @Test
    void modifyingTest() {

        Integer total = productRepository.deleteProductUsingName("Wrong");
        Assertions.assertEquals(0, total);

        total = productRepository.updateProductPriceToZero(1L);
        Assertions.assertEquals(1, total);

        Product product = productRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(0, product.getPrice());
    }

    @Test
    void stream() {
        transactionOperations.executeWithoutResult(transactionStatus -> {

            Category category = categoryRepository.findById(1L).orElse(null);
            Assertions.assertNotNull(category);

            Stream<Product> stream = productRepository.streamAllByCategory(category);
            stream.forEach(product ->
                    System.out.println(product.getId() + " : " + product.getName()));

        });

    }

    @Test
    void sliceTest() {
        Pageable pageable = PageRequest.of(0, 1);

        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        Slice<Product> products = productRepository.findAllByCategory(category, pageable);

        System.out.println("------------------------");
        System.out.println(products.getContent().get(0).getName());
        System.out.println("------------------------");
        products = productRepository.findAllByCategory(category, products.nextPageable());
        System.out.println(products.getContent().get(0).getName());

//        while (products.hasNext()){
//            System.out.println(products.getContent().get(0).getName());
//            products = productRepository.findAllByCategory(category, products.nextPageable());
//        }
    }

    @Test
    void lock1() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            try {
                Product product = productRepository.findFirstById(1L).orElse(null);
                Assertions.assertNotNull(product);
                product.setPrice(15_000_000L);

                Thread.sleep(20_000L);

                productRepository.save(product);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void lock2() {
        transactionOperations.executeWithoutResult(transactionStatus -> {

            Product product = productRepository.findFirstById(1L).orElse(null);
            Assertions.assertNotNull(product);
            product.setPrice(10_000_000L);

            productRepository.save(product);

        });
    }

    @Test
    void specification() {
        Specification<Product> specification = (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaQuery.where(
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("name"), "%Poco%"),
                            criteriaBuilder.like(root.get("name"), "%Samsung%")
                    )
            ).getRestriction();
        };

        List<Product> products = productRepository.findAll(specification);
        Assertions.assertEquals(2, products.size());
    }

    @Test
    void projection() {
        List<SimpleProduct> simpleProducts = productRepository.findAllByNameLike("%Poco%", SimpleProduct.class);
        Assertions.assertEquals(1, simpleProducts.size());

        Assertions.assertEquals("Poco F51", simpleProducts.get(0).name());
        simpleProducts.stream().forEach(simpleProduct -> {
            System.out.println(simpleProduct.id() + " : " + simpleProduct.name());
        });
    }

    @Test
    void projection2() {
        List<ProductPrice> productPrices = productRepository.findAllByNameLike("%Poco%", ProductPrice.class);
        Assertions.assertEquals(1, productPrices.size());

        productPrices.stream().forEach(productPrice -> {
            System.out.println(productPrice.id() + " : " + productPrice.price());
        });
    }
}
