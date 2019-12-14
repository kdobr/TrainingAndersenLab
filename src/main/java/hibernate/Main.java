package hibernate;

import hibernate.dao.AuthorDAO;
import hibernate.dao.BookDAO;
import hibernate.dao.ColumnistDAO;
import hibernate.dao.JournalDAO;
import hibernate.model.writers.Columnist;
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
        JournalDAO journalDAO = new JournalDAO(factory);
        ColumnistDAO columnistDAO = new ColumnistDAO(factory);
//authorDAO.addAuthor("Peter");
      //  authorDAO.updateAuthor("Peter", "Leo");
      //  bookDAO.addBook("War and piece");
      //  journalDAO.addJournal("Time");
//columnistDAO.updateColumnist("pit", "Pit");
//authorDAO.addBookToAuthor("Leo", "War and Pies");
//columni
journalDAO.printAllEditions();
columnistDAO.printAllWritingPersons();


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
