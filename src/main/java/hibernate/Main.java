package hibernate;

import hibernate.dao.AuthorDAO;
import hibernate.dao.BookDAO;
import hibernate.exeptions.MyAppException;
import hibernate.exeptions.MyAppExceptionNoAuthor;
import hibernate.model.Author;
import hibernate.model.Book;
import hibernate.util.DBHelper;

import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
        SessionFactory factory = DBHelper.getSessionFactory();
        BookDAO bookDAO = new BookDAO(factory);
        AuthorDAO authorDAO = new AuthorDAO(factory);
//        Author Jim = new Author("Jim");
//        Jim.addBook(new Book("Jim's book"));
        authorDAO.addBookToAuthor("Bob", "Green Book");
        //bookDAO.getAllBooks();
//        System.out.println(bookDAO.getBookByTitle("Sixthh"));
//
//        System.out.println(bookDAO.getAllAuthorsOfBook("Sixthh"));

      //  System.out.println(author.getBookList());
      //  authorDAO.deleteAuthor("Jim");
//        System.out.println(authorDAO.getAuthorByName("Bob"));
//       System.out.println(authorDAO.getAuthorById(20));
      //  authorDAO.updateAuthor("BAAb", "Bob");
//        System.out.println(authorDAO.getAuthorById(5));

      //  System.out.println(authorDAO.getAllAuthors());
       // System.out.println(authorDAO.getAuthorByName("Bobb").orElseThrow(new MyAppExceptionNoAuthor()));


    }


    public static Connection getMysqlConnection() {

        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            return DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/db_example?serverTimezone=UTC&useSSL=false", "root", "1234");
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
