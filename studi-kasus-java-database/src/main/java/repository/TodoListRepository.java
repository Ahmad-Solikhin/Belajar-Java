package repository;

import entity.Todolist;

import java.util.List;

public interface TodoListRepository {

  List<Todolist> getAll();

  void add(Todolist todolist);

  boolean remove(Integer number);

}
