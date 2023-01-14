import com.zaxxer.hikari.HikariDataSource;
import repository.TodoListRepository;
import repository.TodoListRepositoryImpl;
import service.TodoListService;
import service.TodoListServiceImpl;
import util.DatabaseUtil;
import view.TodoListView;

public class AplikasiTodolistV2 {

  public static void main(String[] args) {
    HikariDataSource dataSource = DatabaseUtil.getDataSource();
    TodoListRepository todoListRepository = new TodoListRepositoryImpl(dataSource);
    TodoListService todoListService = new TodoListServiceImpl(todoListRepository);
    TodoListView todoListView = new TodoListView(todoListService);

    todoListView.showTodoList();
  }
}
