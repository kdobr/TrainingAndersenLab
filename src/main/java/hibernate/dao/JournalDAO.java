package hibernate.dao;


import hibernate.exeptions.AuthorExistsExceprion;
import hibernate.exeptions.BookNotExistsExceprion;
import hibernate.model.editions.Edition;
import hibernate.model.editions.Journal;
import hibernate.model.writers.Author;
import hibernate.model.writers.Columnist;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JournalDAO {

    private final SessionFactory factory;

    public JournalDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void addJournal(String title) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Journal> journalOpt = findJournalByTitle(title, session);
            journalOpt.ifPresent(a -> {
                throw new AuthorExistsExceprion(title);
            });
            session.save(new Journal(title));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void deleteJournal(String title) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Journal> journalOpt = findJournalByTitle(title, session);
            session.delete(journalOpt.orElseThrow(() -> new BookNotExistsExceprion(title)));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Journal getJournalByTitle(String title) {
        Transaction transaction = null;
        Journal journal = null;
        try (Session session = factory.openSession()) {
            Optional<Journal> bookOpt = findJournalByTitle(title, session);
            journal = bookOpt.orElseThrow(() -> new BookNotExistsExceprion(title));
            transaction = session.beginTransaction();
            int aTemp = journal.getColumnistList().size();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return journal;
    }

    public Journal getJournalById(int id) {
        Journal journal = null;
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            journal = session.get(Journal.class, id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return journal;
    }

    public List<Journal> getAllJournals() {
        List<Journal> journalList = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            journalList = session.createQuery("FROM Journal").getResultList();
            transaction.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return journalList;
    }

    public List<Columnist> getAllColumnistOfJournal(String title) {
        Journal journal = getJournalByTitle(title);
        return journal.getColumnistList();
    }

    public void updateJournal(String oldTitle, String newTitle) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Journal> bookCheck = findJournalByTitle(oldTitle, session);
            Journal journal = bookCheck.orElseThrow(() -> new BookNotExistsExceprion(oldTitle));
            journal.setTitle(newTitle);
            transaction.commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private Optional<Journal> findJournalByTitle(String title, Session session) {

        String hql = "from Journal where title = :title";
        Query query = session.createQuery(hql);
        query.setParameter("title", title);
        List<Journal> tempList = query.getResultList();
        if (tempList.size() != 0) {
            return Optional.of(tempList.get(0));
        } else {
            return Optional.empty();
        }
    }

    private void printRollBackError() {
        System.err.println("Couldn’t roll back transaction");
    }

    private void printTransactionError() {
        System.err.println("Error occured, transaction rolled back");
    }
}
