package service;

import entity.Todolist;
import repository.TodoListRepository;

import java.util.List;

public class TodoListServiceImpl implements TodoListService {

  private TodoListRepository todoListRepository;

  public TodoListServiceImpl(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  @Override
  public void showTodoList() {
    List<Todolist> model = todoListRepository.getAll();

    System.out.println("TODOLIST");
    //pake lambda
    model.forEach(v -> System.out.println(v.getId() + ". " + v.getTodo()));
  }

  @Override
  public void addTodoList(String todo) {
    Todolist todolist = new Todolist(todo);
    todoListRepository.add(todolist);
    System.out.println("SUKSES MENAMBAH TODO : " + todo);
  }

  @Override
  public void removeTodoList(Integer number) {
    boolean success = todoListRepository.remove(number);
    if (success) {
      System.out.println("SUKSES MENGHAPUS TODO : " + number);
    } else {
      System.out.println("GAGAL MENGHAPUS TODO : " + number);
    }
  }
}
