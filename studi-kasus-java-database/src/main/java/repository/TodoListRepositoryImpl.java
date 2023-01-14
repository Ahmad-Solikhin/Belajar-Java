package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Todolist;
import util.DatabaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListRepositoryImpl implements TodoListRepository {

  private HikariDataSource dataSource;

  public TodoListRepositoryImpl(HikariDataSource dataSource){
    this.dataSource = dataSource;
  }

  @Override
  public List<Todolist> getAll() {
    String sql = "SELECT * FROM todolist";

    try (Connection connection = DatabaseUtil.getDataSource().getConnection();
         Statement statement = connection.createStatement();
         ResultSet result = statement.executeQuery(sql)){

      List<Todolist> list = new ArrayList<>();
      while (result.next()){
        Todolist todo = new Todolist();
        todo.setId(result.getInt("id"));
        todo.setTodo(result.getString("todo"));

        list.add(todo);
      }

      return list;

    }catch (SQLException e){
      throw new RuntimeException(e);
    }
  }

  @Override
  public void add(Todolist todolist) {
    String sql = "INSERT INTO todolist(todo) VALUES (?)";

    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)){

      statement.setString(1, todolist.getTodo());
      statement.executeUpdate();

    }catch (SQLException exception){
      throw new RuntimeException(exception);
    }
  }

  private boolean findById(Integer number){
    String sql = "SELECT * FROM todolist WHERE id = ?";

    try (Connection connection = DatabaseUtil.getDataSource().getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)){

      statement.setInt(1, number);

      try (ResultSet result = statement.executeQuery()){
        return (result.next()) ? true : false;
      }
    }catch (SQLException e){
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean remove(Integer number) {

    if (findById(number)){
      String sql = "DELETE FROM todolist WHERE id = ?";

      try (Connection connection = DatabaseUtil.getDataSource().getConnection();
           PreparedStatement statement = connection.prepareStatement(sql)){

        statement.setInt(1, number);
        statement.executeUpdate();

        return true;
      }catch (SQLException exception){
        throw new RuntimeException(exception);
      }
    }else {
      return false;
    }
  }
}
