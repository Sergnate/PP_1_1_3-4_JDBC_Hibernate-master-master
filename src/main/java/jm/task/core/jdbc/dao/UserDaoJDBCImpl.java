package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = Util.connection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT auto_increment, name VARCHAR(64), lastName VARCHAR(64), age INT(3), primary key (id))");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.connection().createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException exception) {
            exception.getStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement =
                     Util.connection().prepareStatement("INSERT INTO users(name,lastName,age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User c именем - %s добавлен в базу данных%n", name);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement =
                     Util.connection().prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.getStackTrace();
        }

    }

    public List<User> getAllUsers() {
        try (Statement statement = Util.connection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            List<User> allUsers = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                allUsers.add(user);
            }
            return allUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void cleanUsersTable() {
        try (Statement statement = Util.connection().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }


}
