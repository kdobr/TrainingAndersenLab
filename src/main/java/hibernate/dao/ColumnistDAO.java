package hibernate.dao;

import hibernate.exeptions.AuthorExistsExceprion;
import hibernate.exeptions.AuthorNotExistsExceprion;
import hibernate.model.editions.Edition;
import hibernate.model.editions.Journal;
import hibernate.model.writers.Columnist;
import hibernate.model.writers.WritingPerson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ColumnistDAO {

    private final SessionFactory factory;
    private final JournalDAO journalDAO;


    public ColumnistDAO(SessionFactory factory) {
        this.factory = factory;
        journalDAO = new JournalDAO(factory);
    }

    public void addColumnist(String name) {

        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Columnist> colOpt = findColumnistByName(name, session);
            colOpt.ifPresent(a -> {
                throw new AuthorExistsExceprion(name);
            });
            session.save(new Columnist(name));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
        }
    }

    public void deleteColumnist(String name) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Columnist> columnistOpt = findColumnistByName(name, session);
            session.delete(columnistOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name)));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
        }
    }

    public Columnist getColumnistByName(String name) {
        Transaction transaction = null;
        Columnist columnist = null;
        try (Session session = factory.openSession()) {
            Optional<Columnist> columnistOpt = findColumnistByName(name, session);
            columnist = columnistOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name));
            transaction = session.beginTransaction();
            int aTemp =columnist.getJournalList().size();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
        return columnist ;
    }

    public boolean addJournalToColumnist(String name, String title) {
        Transaction transaction = null;
        Columnist columnist = null;
        try (Session session = factory.openSession()) {
            Optional<Columnist> columnistOpt = findColumnistByName(name, session);
            columnist = columnistOpt.orElseThrow(() -> new AuthorNotExistsExceprion(name));
            transaction = session.beginTransaction();
            journalDAO.addJournal(title);
            Journal journal = journalDAO.getJournalByTitle(title);
            columnist.getJournalList().add(journal);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return true;
    }

    public Columnist getColumnistById(int id) {
        Columnist columnist = null;
        Transaction transaction = null;
        try (Session session = factory.openSession()) {

            transaction = session.beginTransaction();
            columnist = session.get(Columnist.class, id);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return columnist;
    }

    public List<Columnist> getAllColumnist() {
        List<Columnist> columnistList = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            columnistList = session.createQuery("FROM Columnist").getResultList();
            transaction.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return columnistList;
    }

    public void updateColumnist(String oldName, String newName) {

        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Columnist> columnistCheck = findColumnistByName(oldName, session);
            Columnist columnist = columnistCheck.orElseThrow(() -> new AuthorNotExistsExceprion(oldName));
            columnist.setName(newName);
            transaction.commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private Optional<Columnist> findColumnistByName(String name, Session session) {

        String hql = "from Columnist where name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        List<Columnist> tempList = query.getResultList();
        if (tempList.size() != 0) {
            return Optional.of(tempList.get(0));
        } else {
            return Optional.empty();
        }
    }

    public void printAllWritingPersons(){
        Session session = factory.openSession();
        String hql = "SELECT b FROM WritingPerson b";
        Query query = session.createQuery(hql);
        List<WritingPerson> list = query.getResultList();
        System.out.println(list);
        session.close();
    }

    private void printRollBackError() {
        System.err.println("Couldn’t roll back transaction");
    }

    private void printTransactionError() {
        System.err.println("Error occured, transaction rolled back");
    }
}
