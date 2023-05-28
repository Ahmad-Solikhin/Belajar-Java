package com.gayuh.webmvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    private List<String> todos = new ArrayList<>();

    @PostMapping("/todos")
    public ResponseEntity<List<String>> addTodo(@RequestParam(name = "todo") String todo){
        this.todos.add(todo);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<String>> getTodos(){
        return ResponseEntity.ok(todos);
    }

}
