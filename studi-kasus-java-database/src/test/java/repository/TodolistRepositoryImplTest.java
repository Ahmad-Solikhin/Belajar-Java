package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Todolist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DatabaseUtil;

import java.util.List;
import java.util.function.Consumer;

public class TodolistRepositoryImplTest {

    private HikariDataSource dataSource;

    private TodoListRepository todoListRepository;

    @BeforeEach
    void setUp(){
        dataSource = DatabaseUtil.getDataSource();
        todoListRepository = new TodoListRepositoryImpl(dataSource);
    }

    @Test
    void testAdd(){
        Todolist todolist = new Todolist();
        todolist.setTodo("Tidur");

        todoListRepository.add(todolist);
    }

    @Test
    void testRemove(){
        System.out.println(todoListRepository.remove(1));
        System.out.println(todoListRepository.remove(2));
    }

    @Test
    void testGetAll(){
        todoListRepository.add(new Todolist("Tidur"));
        todoListRepository.add(new Todolist("Makan"));

        List<Todolist> list = todoListRepository.getAll();

        list.forEach(v -> System.out.println(v.getId() + " : " + v.getTodo()));
    }

    @AfterEach
    void tearDown(){
        dataSource.close();
    }

}
