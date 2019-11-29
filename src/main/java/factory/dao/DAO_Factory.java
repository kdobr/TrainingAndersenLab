package factory.dao;

public class DAO_Factory {
    public static UserDAO getUserDAO(String daoType) {
        UserDAO userDAO = null;
        if (daoType.equals("hibernate")) {
            userDAO = new UserDAOHibernateImpl();
        } else if (daoType.equals("jdbc")) {
            userDAO = new UserDAOjdbcImpl();
        }
        return userDAO;
    }
}
