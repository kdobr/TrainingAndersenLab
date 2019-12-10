package jdbc.dao;

import jdbc.Main;
import jdbc.dao.executor.Executor;
import jdbc.model.Author;
import jdbc.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    private Executor executor;

    public AuthorDAO() {
        executor = new Executor();
    }

    public void addAuthor(String name) {
        String sql = "insert into author (name) values (?)";
        try (Connection connection = Main.getMysqlConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAuthor(String name) {
        String sql = "delete from author where name=?";
        try (Connection connection = Main.getMysqlConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Author getAuthorByName(String name) throws SQLException {
        String sql = "select a.id as authId," +
                "       a.name, " +
                "       b.id as bookId," +
                "       b.title " +
                " from author a " +
                "      left join auth_book ab on a.id = ab.auth_id " +
                "      left join book b on ab.book_id = b.id " +
                "where a.name=?";
        try (Connection connection = Main.getMysqlConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            return executor.execQueryPrep(stmt, this::mapToAuthor);
        }
    }


    public Author getAuthorById(int id) throws SQLException {
        String sql = "select a.id as authId," +
                "       a.name, " +
                "       b.id as bookId," +
                "       b.title " +
                " from author a " +
                "      left join auth_book ab on a.id = ab.auth_id " +
                "      left join book b on ab.book_id = b.id " +
                "where a.id=?";
        try (Connection connection = Main.getMysqlConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return executor.execQueryPrep(stmt, this::mapToAuthor);
        }
    }

    public List<Author> getAllAuthors() throws SQLException {
        return executor.execQuery("select * from author", this::mapToAuthorList);
    }

    public List<Author> getAllAuthorsOfBooks(String title) throws SQLException {
        String sql = "select a.id," +
                "            a.name" +
                " from author a " +
                "      left join auth_book ab on a.id = ab.auth_id " +
                "where book_id = " +
                "(select id " +
                "from book " +
                "where title=?)";

        try (Connection connection = Main.getMysqlConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            return executor.execQueryPrep(stmt, this::mapToAuthorList);
        }
    }

    public void updateAuthor(String oldName, String newName) throws SQLException {
        String sql = "update author set name=? where name=?";
        try (Connection connection = Main.getMysqlConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setString(2, oldName);
            stmt.executeUpdate();
        }
    }

    private Author mapToAuthor(ResultSet result) throws SQLException {
        if (result.next()) {
            List<Book> bookList = new ArrayList<>();
            bookList.add(new Book(result.getInt("bookId"), result.getString("title")));
            Author author = new Author(result.getInt("authId"),
                    result.getString("name"));
            while (result.next()) {
                bookList.add(new Book(result.getInt("bookId"), result.getString("title")));
            }
            author.setBookList(bookList);
            return author;
        }
        return null;
    }

    private List<Author> mapToAuthorList(ResultSet result) throws SQLException {
        List<Author> authList = new ArrayList<>();
        while (result.next()) {
            authList.add(new Author(result.getInt(1), result.getString(2)));
        }
        return authList;
    }
}
