package hibernate.dao;


import hibernate.model.editions.Edition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class EditionDAO {

    private final SessionFactory factory;

    public EditionDAO(SessionFactory factory) {
        this.factory = factory;
    }


    public void printAllEditions() {
        Session session = factory.openSession();
        String hql = "SELECT b FROM Edition b";
        Query query = session.createQuery(hql);
        List<Edition> list = query.getResultList();
        System.out.println(list);
        session.close();
    }
}
