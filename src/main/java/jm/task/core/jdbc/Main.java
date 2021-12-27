package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Max", "Mad", (byte) 30);
        userService.saveUser("John", "Smith", (byte) 35);
        userService.saveUser("Вася", "Пупкин", (byte) 24);
        userService.saveUser("Иван", "Иванов", (byte) 18);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
