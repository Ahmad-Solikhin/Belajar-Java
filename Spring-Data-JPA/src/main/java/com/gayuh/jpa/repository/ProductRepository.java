package com.gayuh.jpa.repository;

import com.gayuh.jpa.entity.Category;
import com.gayuh.jpa.entity.Product;
import com.gayuh.jpa.model.SimpleProduct;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    //Projection
    //List<SimpleProduct> findAllByNameLike(String name);

    //Ini kalo mau pake record yang mirip2x
    <T> List<T> findAllByNameLike(String name, Class<T> tClass);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findFirstById(Long id);
    Slice<Product> findAllByCategory(Category category, Pageable pageable);

    Stream<Product> streamAllByCategory(Category category);

    @Transactional
    @Modifying
    @Query(value = "delete from Product p where p.name = :name")
    Integer deleteProductUsingName(@Param("name") String name);
    @Transactional
    @Modifying
    @Query(value = "update Product p set p.price = 0 where p.id = :id")
    Integer updateProductPriceToZero(@Param("id") Long id);
    @Query(
            value = "select p from Product p where p.name like :name or p.category.name like :name",
            countQuery = "select count(p) from Product p where p.name like :name or p.category.name like :name"
    )
    Page<Product> searchProduct(@Param("name") String name, Pageable pageable);
    List<Product> searchProductUsingName(@Param("name") String name, Pageable pageable);
    @Transactional
    Integer deleteByName(String name);
    Boolean existsByNameLike(String name);
    List<Product> findAllByCategory_Name(String name);
    List<Product> findAllByCategory_Name(String name, Sort sort);
    Page<Product> findAllByCategory_Name(String name, Pageable pageable);
    Long countByCategory_Name(String name);
}
