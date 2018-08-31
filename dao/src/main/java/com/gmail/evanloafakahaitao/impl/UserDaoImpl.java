package com.gmail.evanloafakahaitao.impl;

import com.gmail.evanloafakahaitao.UserDao;
import com.gmail.evanloafakahaitao.model.User;
import com.gmail.evanloafakahaitao.util.UserConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private UserConverter userConverter = new UserConverter();

    @Override
    public List<User> findAll(Connection connection) {
        String findAllUsers = "select u.id, u.email, u.password, u.first_name, u.last_name, u.phone_number, u.add_info, r.name as role from user u join role r on u.role_id = r.id";
        List<User> userList = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(findAllUsers);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                User user = userConverter.toUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findByEmail(Connection connection, String email) {
        String findUserByEmail = "select u.id, u.email, u.password, u.first_name, u.last_name, u.phone_number, u.add_info, r.name as role from user u join role r on u.role_id = r.id where u.email = ?";
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(findUserByEmail)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = userConverter.toUser(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }
}
