package com.example.demo1.model.dao.impl;

import com.example.demo1.model.dao.UserDao;
import com.example.demo1.model.dao.mapper.UserMapper;
import com.example.demo1.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.example.demo1.containers.QueryContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class JDBCUserDao implements UserDao {
    private Connection connection;
    private UserMapper userMapper = new UserMapper();
    private Map<Integer, User> users = new HashMap<>();

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method for inserting new user
     * @param entity to insert
     */
    @Override
    public void create(User entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getFullName());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getAddress());
            statement.setString(6, entity.getPhoneNumber());
            statement.setString(7, entity.getBalance().toString());
            statement.setString(8, entity.getRole());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for searching user by id
     * @param id to search
     * @return user found
     */
    @Override
    public User findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setInt(1, id);

            return findUser(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    /**
//     * Method for searching for user by surname
//     * @param surname to search
//     * @param query to db
//     * @return user found
//     */
//    @Override
//    public User findBySurname(String surname, String query) {
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, surname);
//
//            return findUser(statement);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * Method for searching for user by login
     * @param login to search
     * @return user found
     */
    @Override
    public User findByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);

            return findUser(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method for searching for user by login and password
     * @param login to search
     * @param password to search
     * @return user found
     */
    @Override
    public User findByLoginAndPassword(String login, String password) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);

            return findUser(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method for fetching all of users from the table
     * @return list of users
     */
    @Override
    public List<User> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS)) {

            return findUsersList(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method for deleting user by id
     * @param id to delete
     */
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(User entity) {
    }

    /**
     * Method for updating user password by id
     * @param id of user to change
     * @param newPassword that must be inserted except the old one
     */
    @Override
    public void updatePassword(Integer id, String newPassword) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setInt(2, id);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for finding users by role
     * @param role of users, that should be found
     * @return list of users found
     */
    @Override
    public List<User> findByRole(String role) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ROLE)) {
            statement.setString(1, role);

            return findUsersList(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method for closing connection to db
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for executing statement and fetching user data from result set.
     * Method to eliminate duplicate code
     * @param statement to execute
     * @return user found
     * @throws SQLException
     */
    private User findUser(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        User user = null;

        if (resultSet.next()) {
            user = userMapper.extractFromResultSet(resultSet);
        }
        if (Objects.nonNull(user)) {
            userMapper.makeUnique(users, user);
        }
        resultSet.close();

        return user;
    }

    /**
     * Method for executing statement and fetching users data from result set.
     * Method to eliminate duplicate code
     * @param statement to execute
     * @return list of users found
     * @throws SQLException
     */
    private List<User> findUsersList(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            User user = userMapper.extractFromResultSet(resultSet);
            userMapper.makeUnique(users, user);
        }
        resultSet.close();

        return new ArrayList<>(users.values());
    }
}

