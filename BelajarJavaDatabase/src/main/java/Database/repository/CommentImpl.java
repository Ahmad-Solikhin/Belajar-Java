package Database.repository;

import Database.ConnectionUtil;
import Database.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentImpl implements CommentRepository{

    @Override
    public void add(Comment comment) {
        try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

            try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, comment.getEmail());
                statement.setString(2, comment.getComment());
                statement.executeUpdate();

                try (ResultSet result = statement.getGeneratedKeys()){
                    if (result.next()) comment.setId(result.getInt(1));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment findById(Integer id) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM comments WHERE id = ?";

            try (var statement = connection.prepareStatement(sql)){
                statement.setInt(1, id);

                try (var result = statement.executeQuery()){
                    if (result.next()){
                        return new Comment(
                                result.getInt("id"),
                                result.getString("email"),
                                result.getString("comment")
                        );
                    }else {
                        return null;
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findAll() {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM comments";

            try (var statement = connection.createStatement()){

                try (var result = statement.executeQuery(sql)){
                    List<Comment> comments = new ArrayList<>();

                    while (result.next()){
                        comments.add(new Comment(
                                result.getInt("id"),
                                result.getString("email"),
                                result.getString("comment")
                        ));
                    }
                    return comments;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
