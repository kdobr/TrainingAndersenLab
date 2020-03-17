package hibernate.model.writers;

import hibernate.model.editions.Journal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("Columnist")
public class Columnist extends WritingPerson{


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "auth_book",
            joinColumns = @JoinColumn(name = "auth_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Journal> journalList;

    public Columnist() {
    }

    public Columnist(String name) {
        super(name);
    }

    public List<Journal> getJournalList() {
        return journalList ;
    }

    public void setJournalList(List<Journal> journalList) {
        this.journalList = journalList;
    }


    public void addJournal(Journal journal) {
        if (journalList == null) {
            journalList = new ArrayList<>();
        }
        journalList.add(journal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Columnist)) return false;
        Columnist columnist = (Columnist) o;
        return Objects.equals(getJournalList(), columnist.getJournalList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJournalList());
    }

    @Override
    public String toString() {
        return "Columnist{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                               '}';
    }
}
