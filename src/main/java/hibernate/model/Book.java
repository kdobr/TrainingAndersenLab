package hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int  id;
    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "auth_book",
            joinColumns =@JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id"))
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

    public void addAuthor(Author author) {
        if (authorList == null) {
            authorList = new ArrayList<>();
        }
        authorList.add(author);
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
