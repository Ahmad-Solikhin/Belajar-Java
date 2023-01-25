//package spring.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import spring.domain.Author;
//import spring.domain.Book;
//import spring.repository.BookRepository;
//import spring.repository.impl.BookRepositoryImpl;
//import spring.service.BookService;
//import spring.service.impl.BookServiceImpl;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class AppConfig {
//
//    //Contoh menambahkan isi atau value dari object yang dibuat menggunakan bean config
//    @Bean
//    public Author author(){
//        Author author = new Author();
//        author.setId(1);
//        author.setName("Gayuh");
//        author.setBirthDate(1002434450L);
//
//        return author;
//    }
//
//    @Bean
//    public Book book1(Author author){
//        Book book = new Book(author);
//        book.setId(1);
//        book.setTitle("Gatau");
//        book.setDescription("Namanya Orang Gatau ya gatau");
//
//        return book;
//    }
//
//    //Membuat IoC dimana saat membuat class dari interface akan terbuat object dari class impl nya
//    @Bean
//    public BookRepository bookRepository(Book book1){
//        Map<Integer, Book> map = new HashMap<>();
//        map.put(1, book1);
//
//        BookRepositoryImpl bookRepository = new BookRepositoryImpl();
//        bookRepository.setBookMap(map);
//
//        return  bookRepository;
//    }
//
//    @Bean
//    public BookService bookService(BookRepository bookRepository){
//        return new BookServiceImpl(bookRepository);
//    }
//
//}
