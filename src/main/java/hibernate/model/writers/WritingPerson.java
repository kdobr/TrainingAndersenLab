package hibernate.model.writers;

import hibernate.model.editions.Book;
import hibernate.model.editions.Edition;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "author")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "WRITER_TYPE")
public abstract class WritingPerson {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;


    public WritingPerson() {
    }

    public WritingPerson(String name) {
        this.name = name;
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
    public String toString() {
        return this.getClass().getName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
