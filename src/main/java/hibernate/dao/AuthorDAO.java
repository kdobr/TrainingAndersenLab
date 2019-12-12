package hibernate.dao;

import hibernate.exeptions.MyAppException;
import hibernate.exeptions.MyAppExceptionNoAuthor;
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

public class AuthorDAO {

    private SessionFactory factory;
    BookDAO bookDAO;


    public AuthorDAO(SessionFactory factory) {
        this.factory = factory;
        bookDAO = new BookDAO(factory);
    }

    public void addAuthor(String name) throws MyAppException {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Optional<Author> author = validateAuthorByName(name, session);
            if (author.isPresent()) {
                throw new MyAppException("this user already exist");
            }
            session.save(new Author(name));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void deleteAuthor(String name) throws MyAppException {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Optional<Author> authorOpt = validateAuthorByName(name, session);
            if (!authorOpt.isPresent()) {
                throw new MyAppException("no such user");
            }
            session.delete(authorOpt.get());
            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public Author getAuthorByName(String name) {
        Session session = factory.openSession();
        Author author = null;
        Optional<Author> authOpt = validateAuthorByName(name, session);
        if (!authOpt.isPresent()) {
            throw new MyAppException("this author not exists");
        }
        try {
            session.beginTransaction();
            author = authOpt.get();
            int aTemp = author.getBookList().size();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return author;
    }

    public boolean addBookToAuthor(String name, String title) {
        Session session = factory.openSession();
        Author author = null;
        Optional<Author> authOpt = validateAuthorByName(name, session);
        if (!authOpt.isPresent()) {
            throw new MyAppException("this author not exists");
        }
        try {
            session.beginTransaction();
            bookDAO.addBook(title);
            Book book = bookDAO.getBookByTitle(title);
            author = authOpt.get();
            author.getBookList().add(book);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return true;
    }

    public Author getAuthorById(int id) {
        Author author = null;
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            author = session.get(Author.class, id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return author;
    }

    public List<Author> getAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        Session session = factory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            authorList = session.createQuery("FROM Author").getResultList();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return authorList;
    }

    public void updateAuthor(String oldName, String newName) throws MyAppException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {

            Optional<Author> authorCheck = validateAuthorByName(oldName, session);
            if (!authorCheck.isPresent()) {
                throw new MyAppException("no such Author");
            }
            authorCheck.get().setName(newName);
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

    private Optional<Author> validateAuthorByName(String name, Session session) {

        Optional<Author> authorOpt = Optional.empty();
        String hql = "from Author where name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        authorOpt = (Optional<Author>) (query.getResultList().size() > 0 ? Optional.of(query.getResultList().get(0)) : authorOpt);
        return authorOpt;
    }

    private void printRollBackError() {
        System.err.println("Couldn’t roll back transaction");
    }

    private void printTransactionError() {
        System.err.println("Error occured, transaction rolled back");
    }
}
