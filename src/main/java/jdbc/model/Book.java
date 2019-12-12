package jdbc.model;

import java.util.List;
import java.util.Objects;

public class Book {

    private int  id;
    private String title;
    private List<Author> authorList;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(String title, List<Author> authorList) {
        this.title = title;
        this.authorList = authorList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getId() == book.getId() &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getAuthorList(), book.getAuthorList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthorList());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                                '}';
    }
}
