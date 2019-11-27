package factory.dao;

public class UserDAOHibernateImpl implements UserDAO {

    @Override
    public void printMyName() {
        System.out.println("I'm hibernate");
    }
}
