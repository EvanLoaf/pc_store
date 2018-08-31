package com.gmail.evanloafakahaitao.impl;

import com.gmail.evanloafakahaitao.UserDao;
import com.gmail.evanloafakahaitao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.model.User;
import com.gmail.evanloafakahaitao.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private ConnectionService connectionService = new ConnectionService();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        List<User> listOfUsers = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("Retrieving all users...");
                connection.setAutoCommit(false);
                listOfUsers = userDao.findAll(connection);
                connection.commit();
                System.out.println("Users found : " + listOfUsers.size());
            } catch (SQLException e) {
                System.out.println("Error retrieving all Users with full info");
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return listOfUsers;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("Finding user by email...");
                connection.setAutoCommit(false);
                user = userDao.findByEmail(connection, email);
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Error retrieving User by email");
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }
}
