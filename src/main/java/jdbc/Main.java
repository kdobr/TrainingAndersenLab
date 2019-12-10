package jdbc;

import jdbc.dao.BookDAO;
import jdbc.dao.AuthorDAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception{

        BookDAO bookDAO = new BookDAO();
        AuthorDAO authorDAO = new AuthorDAO();
        System.out.println(authorDAO.getAuthorByName("Michel"));
        System.out.println(authorDAO.getAuthorById(2));
        System.out.println(authorDAO.getAuthorById(3));
        System.out.println(authorDAO.getAllAuthors());
        authorDAO.deleteAuthor("Tempt");
        System.out.println(authorDAO.getAllAuthors());
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
