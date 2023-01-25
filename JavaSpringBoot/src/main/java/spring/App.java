package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.AppAnnotationAndjavaConfig;
import spring.dto.BookDetailDTO;
import spring.service.BookService;
import spring.service.impl.BookServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //Implementasi lama
//        Scanner scan = new Scanner(System.in);
//        System.out.println( "Hello World!" );
//        System.out.println();
//        BookService bookService = new BookServiceImpl();
//        Integer id = scan.nextInt();
//
//        try{
//            BookDetailDTO dto = bookService.findDetailById(id);
//            System.out.println(dto);
//        }catch (Exception e){
//            System.out.println("Data Tidak Ditemukan");
//        }

        //Implementasi menggunakan annotation IoC
        Scanner scan = new Scanner(System.in);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppAnnotationAndjavaConfig.class);
        BookService bookService = (BookService) applicationContext.getBean("bookService");
        Integer id = scan.nextInt();
        BookDetailDTO dto = bookService.findDetailById(id);
        System.out.println(dto);
    }
}
