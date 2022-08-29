package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Odin", "GodFather", (byte) 99);
        service.saveUser("Tor", "OdinSon", (byte) 25);
        service.saveUser("Mr", "J", (byte) 75);
        service.saveUser("Kata", "A", (byte) 38);
        service.removeUserById(1);
        service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
