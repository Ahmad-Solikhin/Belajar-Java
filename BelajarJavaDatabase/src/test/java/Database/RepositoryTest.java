package Database;

import Database.entity.Comment;
import Database.repository.CommentImpl;
import Database.repository.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositoryTest {

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment(
                "ahmadsgr39@gmail.com",
                "Hallo Guys"
        );
        commentRepository.add(comment);

        Assertions.assertNotNull(comment.getId());
    }

    @Test
    void testFindById() {
        Comment comment = commentRepository.findById(2302);
        Assertions.assertNotNull(comment);
        Assertions.assertEquals(2302, comment.getId());
    }

    @Test
    void testFindByIdNotFond() {
        Comment comment = commentRepository.findById(23021);
        Assertions.assertNull(comment);
    }

    @Test
    void testFindAll() {
        List<Comment> comments = commentRepository.findAll();
        System.out.println("Jumlah Data : " + comments.size());
        System.out.println();
        AtomicInteger y = new AtomicInteger(1);

        comments.forEach(v -> {
            System.out.println("Data ke-" + y);
            System.out.println(v.getId());
            System.out.println(v.getEmail());
            System.out.println(v.getComment());
            System.out.println();
            y.getAndIncrement();
        });

        int x = 1;
        //Pake foreach biasa
        for (var v : comments){
            System.out.println("Data ke-" + x);
            System.out.println(v.getId());
            System.out.println(v.getEmail());
            System.out.println(v.getComment());
            System.out.println();
            x++;
        }
    }
}
