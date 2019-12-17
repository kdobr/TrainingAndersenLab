package hibernate.dao;

import hibernate.exeptions.AuthorExistsExceprion;
import hibernate.exeptions.AuthorNotExistsExceprion;
import hibernate.model.editions.Book;
import hibernate.model.writers.Author;
import hibernate.model.writers.WritingPerson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WritingPersonDAO {

    private final SessionFactory factory;


    public WritingPersonDAO(SessionFactory factory) {
        this.factory = factory;
    }


    public void printAllWritingPersons() {
        Session session = factory.openSession();
        String hql = "SELECT b FROM WritingPerson b";
        Query query = session.createQuery(hql);
        List<WritingPerson> list = query.getResultList();
        System.out.println(list);
        session.close();
    }
}
