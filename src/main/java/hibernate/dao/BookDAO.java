package hibernate.dao;


import hibernate.exeptions.AuthorExistsExceprion;
import hibernate.exeptions.BookNotExistsExceprion;
import hibernate.model.Author;
import hibernate.model.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAO {

    private final SessionFactory factory;

    public BookDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void addBook(String title) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Book> bookOpt = findBookByTitle(title, session);
            bookOpt.ifPresent(a -> {
                throw new AuthorExistsExceprion(title);
            });
            session.save(new Book(title));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void deleteBook(String title) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Book> bookOpt = findBookByTitle(title, session);
            session.delete(bookOpt.orElseThrow(() -> new BookNotExistsExceprion(title)));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Book getBookByTitle(String title) {
        Transaction transaction = null;
        Book book = null;
        try (Session session = factory.openSession()) {
            Optional<Book> bookOpt = findBookByTitle(title, session);
            book = bookOpt.orElseThrow(() -> new BookNotExistsExceprion(title));
            transaction = session.beginTransaction();
            int aTemp = book.getAuthorList().size();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return book;
    }

    public Book getBookById(int id) {
        Book book = null;
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            book = session.get(Book.class, id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            bookList = session.createQuery("FROM Book").getResultList();
            transaction.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public List<Author> getAllAuthorsOfBook(String title) {
        Book book = getBookByTitle(title);
        return book.getAuthorList();
    }

    public void updateBook(String oldTitle, String newTitle) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Book> bookCheck = findBookByTitle(oldTitle, session);
            Book book = bookCheck.orElseThrow(() -> new BookNotExistsExceprion(oldTitle));
            book.setTitle(newTitle);
            transaction.commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private Optional<Book> findBookByTitle(String title, Session session) {

        String hql = "from Book where title = :title";
        Query query = session.createQuery(hql);
        query.setParameter("title", title);
        List<Book> tempList = query.getResultList();
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
