package hibernate;

import hibernate.dao.AuthorDAO;
import hibernate.dao.BookDAO;
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
        System.out.println(bookDAO.getAllAuthorsOfBook("Third"));
        System.out.println(bookDAO.getAllBooks());
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
