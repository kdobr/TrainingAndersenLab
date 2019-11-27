package factory.dao;

public class UserDAOjdbcImpl implements UserDAO {

    @Override
    public void printMyName() {
        System.out.println("I'm jdbc");
    }
}
