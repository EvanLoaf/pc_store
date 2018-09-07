package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.UserDao;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.dao.util.UserConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private UserConverter userConverter = new UserConverter();

    public UserDaoImpl(Class<User> clazz) {
        super(clazz);
    }

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
            logger.error(e.getMessage(), e);
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
            logger.error(e.getMessage(), e);
        }
        return user;
    }
}
