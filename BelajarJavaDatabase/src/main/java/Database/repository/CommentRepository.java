package Database.repository;

import Database.entity.Comment;

import java.util.List;

public interface CommentRepository {

    void add(Comment comment);

    Comment findById(Integer id);

    List<Comment> findAll();
}
