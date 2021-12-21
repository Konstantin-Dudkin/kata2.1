package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Max", "Mad", (byte) 30);
        userDaoJDBC.saveUser("John", "Smith", (byte) 35);
        userDaoJDBC.saveUser("Вася", "Пупкин", (byte) 24);
        userDaoJDBC.saveUser("Иван", "Иванов", (byte) 18);
        System.out.println(userDaoJDBC.getAllUsers());
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
