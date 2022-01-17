package com.usermanagement.dao;

import java.sql.*;
import java.util.*;
import com.usermanagement.model.User;

//Data Access Object
//Provides CRUD database operations for the table users in the database

public class UserDAO {
    static final String DB_URL = "jdbc:mysql://localhost:3306/user_management";
    static final String USER = "skrishnan2001";
    static final String PASS = "sk27032001";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, phone) VALUES "
            + " (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "SELECT id, name, email, phone FROM users where id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET name = ?,email= ?, phone = ? WHERE id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Create User
    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update User
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdate = false;
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setInt(4, user.getId());

            rowUpdate = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdate;
    }

    // Select User By ID
    public User selectUser(int id) {
        User user = null;
        // Establishing the connection
        try (Connection connection = getConnection();
                // Creating a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Executing the query
            ResultSet rs = preparedStatement.executeQuery();

            // Processing the result set object
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                user = new User(id, name, email, phone);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Select all Users
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        // Establishing the connection
        try (Connection connection = getConnection();
                // Creating a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Executing the query
            ResultSet rs = preparedStatement.executeQuery();

            // Processing the result set object
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                users.add(new User(id, name, email, phone));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Delete a User
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}
