package hibernate.util;

import hibernate.model.editions.Edition;
import hibernate.model.editions.Journal;
import hibernate.model.writers.Author;
import hibernate.model.editions.Book;
import hibernate.model.writers.Columnist;
import hibernate.model.writers.WritingPerson;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DBHelper {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }


    private static SessionFactory createSessionFactory() {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Journal.class)
                .addAnnotatedClass(Edition.class)
                .addAnnotatedClass(WritingPerson.class)
                .addAnnotatedClass(Columnist.class)
                .buildSessionFactory();
        return factory;
    }

}
