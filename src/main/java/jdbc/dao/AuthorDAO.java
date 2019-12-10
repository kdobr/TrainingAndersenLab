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

public class AuthorDAO {

    private Executor executor;

    public AuthorDAO() {
        executor = new Executor();
    }

    public void addAuthor(String name) throws SQLException {
        executor.execUpdate("insert into author (name) values ('" + name + "')");
    }

    public boolean deleteAuthor(String name) {
        try {
            executor.execUpdate("delete from author where name = '" + name + "';");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Author getAuthorByName(String name) throws SQLException {
        return executor.execQuery("select a.id as authId, a.name, b.id as bookId,  b.title from author a left join auth_book ab on a.id = ab.auth_id left join book b on ab.book_id = b.id where name='" + name + "'", result -> {
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
        });
    }

    public Author getAuthorById(int id) throws SQLException {
        return executor.execQuery("select a.id as authId, a.name, b.id as bookId,  b.title from author a left join auth_book ab on a.id = ab.auth_id left join book b on ab.book_id = b.id where a.id='" + id + "'", result -> {
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
        });
    }

    public List<Author> getAllAuthors() throws SQLException {
        return executor.execQuery("select * from author", result -> {
            List<Author> authorList = new ArrayList<>();
            while (result.next()) {
                authorList.add(new Author(result.getInt(1), result.getString(2)));
            }
            return authorList;
        });
    }

    public List<Author> getAllAuthorsOfBooks(String title) throws SQLException {
        return executor.
                execQuery("select * from author a left join auth_book ab on a.id = ab.auth_id where book_id = " +
                                "(select id from book where title='" + title + "')"
                        , result -> {
                            List<Author> authList = new ArrayList<>();
                            while (result.next()) {
                                authList.add(new Author(result.getInt(1), result.getString(2)));
                            }
                            return authList;
                        });
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
}
