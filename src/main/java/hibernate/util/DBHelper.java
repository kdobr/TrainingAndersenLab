package hibernate.util;

import hibernate.model.Author;
import hibernate.model.Book;
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
                .buildSessionFactory();
        return factory;
    }

}
