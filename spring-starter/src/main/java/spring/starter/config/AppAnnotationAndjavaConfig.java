package spring.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.starter.domain.Author;
import spring.starter.domain.Book;
import spring.starter.repository.BookRepository;
import spring.starter.repository.impl.BookRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

/***
 * Menambahkan annotation ComponnentScan agar annotation lain bisa melakukan scan pada config ini
 * Dan memberikan parameter package mana yang akan dilakukan pemindaian oleh spring container
*/
@Configuration
public class AppAnnotationAndjavaConfig {

//    //Contoh menambahkan isi atau value dari object yang dibuat menggunakan bean config
//    @Bean
//    public Author author(){
//        Author author = new Author();
//        author.setId(1);
//        author.setName("Gayuh");
//        author.setBirthDate(null);
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
//    @Bean
//    public Book book2(Author author){
//        Book book = new Book(author);
//        book.setId(2);
//        book.setTitle("Buaya");
//        book.setDescription("Namanya Buaya Gatau ya gatau");
//
//        return book;
//    }
//
//    //Membuat IoC dimana saat membuat class dari interface akan terbuat object dari class impl nya
//    @Bean
//    public BookRepository bookRepository(Book book1, Book book2){
//        Map<Integer, Book> map = new HashMap<>();
//        map.put(1, book1);
//        map.put(2, book2);
//
//        BookRepositoryImpl bookRepository = new BookRepositoryImpl();
//        bookRepository.setBookMap(map);
//
//        return  bookRepository;
//    }
}
