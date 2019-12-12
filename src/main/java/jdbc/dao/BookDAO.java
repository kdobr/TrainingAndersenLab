package jdbc.dao;


import jdbc.Main;
import jdbc.dao.executor.Executor;
import jdbc.model.Author;
import jdbc.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private Executor executor;

    public BookDAO() {
        executor = new Executor();
    }

    public void addBook(String title) throws SQLException {
        executor.execUpdate("insert into book (title) values ('" + title + "')");
    }

    public boolean deleteBook(String title) {
        try {
            executor.execUpdate("delete from book where title = '" + title + "';");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Book getBookByTitle(String title) throws SQLException {
        return executor.execQuery("select * from book where title='" + title + "'", result -> {
            if (result.next()) {
                return new Book(result.getInt("id"), result.getString("title"));
            }
            return null;
        });
    }

    public Book getBookById(int id) throws SQLException {
        return executor.execQuery("select * from book where id='" + id + "'", result -> {
            if (result.next()) {
                return new Book(result.getInt("id"), result.getString("title"));
            }
            return null;
        });
    }

    public List<Book> getAllBooks() throws SQLException {
        return executor.execQuery("select * from book", result -> {
            List<Book> bookList = new ArrayList<>();
            while (result.next()) {
                bookList.add(new Book(result.getInt(1), result.getString(2)));
            }
            return bookList;
        });
    }

    public List<Book> getAllBooksOfAuthor(String name) throws SQLException {
        return executor.
                execQuery("select * from book b left join auth_book ab on b.id = ab.book_id where auth_id = " +
                                "(select id from author where name='" + name + "')"
                        , result -> {
                            List<Book> bookList = new ArrayList<>();
                            while (result.next()) {
                                bookList.add(new Book(result.getInt(1), result.getString(2)));
                            }
                            return bookList;
                        });
    }

    public void updateBook(String oldTitle, String newTitle) throws SQLException {
        String sql = "update book set title=? where title=?";
        try (Connection connection = Main.getMysqlConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, newTitle);
            stmt.setString(2, oldTitle);
            stmt.executeUpdate();
        }
    }
}
