package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.UserDao;
import com.gmail.evanloafakahaitao.dao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private ConnectionService connectionService = new ConnectionService();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        List<User> listOfUsers = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                logger.info("Retrieving all users...");
                connection.setAutoCommit(false);
                listOfUsers = userDao.findAll(connection);
                connection.commit();
                logger.info("Users found : " + listOfUsers.size());
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return listOfUsers;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                logger.info("Finding user by email...");
                connection.setAutoCommit(false);
                user = userDao.findByEmail(connection, email);
                connection.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return user;
    }
}
