package factory;

import factory.dao.DAO_Factory;

public class Main {
    public static void main(String[] args) {
        DAO_Factory.getUserDAO("hibernate").printMyName();
        DAO_Factory.getUserDAO("jdbc").printMyName();
    }
}
