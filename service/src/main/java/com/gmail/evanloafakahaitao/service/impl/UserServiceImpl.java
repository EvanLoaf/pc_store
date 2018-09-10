package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.UserDao;
import com.gmail.evanloafakahaitao.dao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.UserService;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.converter.impl.UserDTOConverterImpl;
import com.gmail.evanloafakahaitao.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao = new UserDaoImpl(User.class);
    private DTOConverter userDTOConverter = new UserDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDTO> findAll() {
        /*List<User> listOfUsers = null;
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
        return listOfUsers;*/
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<User> userList = userDao.findAll();
            List<UserDTO> userDTOList = userDTOConverter.toDTOList(userList);
            transaction.commit();
            return userDTOList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve users", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public UserDTO findByEmail(String email) {
        /*User user = null;
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
        return user;*/
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findByEmail(email);
            UserDTO userDTO = (UserDTO) userDTOConverter.toDto(user);
            transaction.commit();
            return userDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve user by email", e);
        }
        return null;
    }
}
