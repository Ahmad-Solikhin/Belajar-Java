package com.gayuh.jpa.service;

import com.gayuh.jpa.entity.Category;
import com.gayuh.jpa.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionOperations;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    private TransactionOperations transactionOperations;

    private PlatformTransactionManager platformTransactionManager;

    public void manual(){
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setTimeout(10);
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus transaction = platformTransactionManager.getTransaction(definition);

        try{
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category " + i);
                categoryRepository.save(category);
            }

            this.error();
            platformTransactionManager.commit(transaction);
        }catch (Throwable throwable){
            platformTransactionManager.rollback(transaction);
            throw throwable;
        }
    }

    public void error(){
        throw new RuntimeException("Ups");
    }

    public void createCategories(){
        transactionOperations.executeWithoutResult(transactionStatus -> {
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category " + i);
                categoryRepository.save(category);
            }

            this.error();
        });
    }

    @Transactional
    public void create(){
        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setName("Category " + i);
            categoryRepository.save(category);
        }
        throw new RuntimeException("Ups rollback");
    }

    public void test(){
        this.create();
    }

}
