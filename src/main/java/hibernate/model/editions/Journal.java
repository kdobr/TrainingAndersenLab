package hibernate.model.editions;

import hibernate.model.writers.Columnist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("Journal")

public class Journal extends Edition {

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "auth_book",
            joinColumns =@JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id"))
    private List<Columnist> columnistList;

    public Journal() {   }

    public Journal(String title) {
        super(title);
    }

    public List<Columnist> getColumnistList() {
        return columnistList;
    }

    public void setColumnistList(List<Columnist> columnistList) {
        this.columnistList = columnistList;
    }

    public void addColumnist(Columnist columnist) {
        if (columnistList == null) {
            columnistList = new ArrayList<>();
        }
        columnistList.add(columnist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journal)) return false;
        if (!super.equals(o)) return false;
        Journal journal = (Journal) o;
        return Objects.equals(getColumnistList(), journal.getColumnistList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getColumnistList());
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + this.getId() +
                ", title='" + this.getTitle() + '\'' +
                '}';
    }
}
