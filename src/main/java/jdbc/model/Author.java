package jdbc.model;

import java.util.List;
import java.util.Objects;

public class Author {

    private int id;
    private String name;
    private List<Book> bookList;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(int id, String name, List<Book> bookList) {
        this.name = name;
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return getId() == author.getId() &&
                Objects.equals(getName(), author.getName()) &&
                Objects.equals(getBookList(), author.getBookList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBookList());
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", List of books: " + bookList +
                '}';
    }
}
