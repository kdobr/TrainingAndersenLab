package hibernate.dao;


import hibernate.exeptions.MyAppException;
import hibernate.exeptions.MyAppExceptionNoAuthor;
import hibernate.exeptions.MyAppExceptionNoBook;
import hibernate.model.Author;
import hibernate.model.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAO {

    private SessionFactory factory;

    public BookDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void addBook(String title) throws MyAppException {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Optional<Book> bookOpt = validateBookByTitle(title, session);
            if (bookOpt.isPresent()) {
                throw new MyAppException("this book exists");
            }
            session.save(new Book(title));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void deleteBook(String title) throws MyAppException {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Optional<Book> bookOpt = validateBookByTitle(title, session);
            if (!bookOpt.isPresent()) {
                throw new MyAppException("no such book");
            }
            session.delete(bookOpt.get());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public Book getBookByTitle(String title) {
        Session session = factory.openSession();
        Book book = null;
        Optional<Book> bookOpt = validateBookByTitle(title, session);
        if (!bookOpt.isPresent()) {
            throw new MyAppException("this book not exists");
        }
        try {
            session.beginTransaction();
            book = bookOpt.get();
            int aTemp = book.getAuthorList().size();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return book;
    }

    public Book getBookById(int id) {
        Book book = null;
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            book = session.get(Book.class, id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        Session session = factory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            bookList = session.createQuery("FROM Book").getResultList();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return bookList;
    }

    public List<Author> getAllAuthorsOfBook(String title) throws SQLException {
        Book book = getBookByTitle(title);
        return book.getAuthorList();
    }

    public void updateBook(String oldTitle, String newTitle) throws MyAppException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {

            Optional<Book> bookCheck = validateBookByTitle(oldTitle, session);
            if (!bookCheck.isPresent()) {
                throw new MyAppException("no such book");
            }
            bookCheck.get().setTitle(newTitle);
            transaction.commit();
        } catch (MyAppException e) {
            throw e;
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
                printTransactionError();
            } catch (Exception eRoll) {
                printRollBackError();
            }
        } finally {
            session.close();
        }
    }


    private Optional<Book> validateBookByTitle(String title, Session session) {

        Optional<Book> bookOpt = Optional.empty();
        String hql = "from Book where title = :title";
        Query query = session.createQuery(hql);
        query.setParameter("title", title);
        bookOpt = (Optional<Book>) (query.getResultList().size() > 0 ? Optional.of(query.getResultList().get(0)) : bookOpt);
        return bookOpt;
    }

    private void printRollBackError() {
        System.err.println("Couldn’t roll back transaction");
    }

    private void printTransactionError() {
        System.err.println("Error occured, transaction rolled back");
    }
}
