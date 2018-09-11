package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.RoleDao;
import com.gmail.evanloafakahaitao.dao.UserDao;
import com.gmail.evanloafakahaitao.dao.impl.RoleDaoImpl;
import com.gmail.evanloafakahaitao.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.Role;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.UserService;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.converter.impl.CommentFeedbackNewsLoginUserDTOConverterImpl;
import com.gmail.evanloafakahaitao.service.converter.impl.UserConverterImpl;
import com.gmail.evanloafakahaitao.service.converter.impl.UserDTOConverterImpl;
import com.gmail.evanloafakahaitao.service.dto.CommentFeedbackNewsLoginUserDTO;
import com.gmail.evanloafakahaitao.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao = new UserDaoImpl(User.class);
    private RoleDao roleDao = new RoleDaoImpl(Role.class);
    private DTOConverter userDTOConverter = new UserDTOConverterImpl();
    private Converter userConverter = new UserConverterImpl();
    private DTOConverter commentFeedbackNewsLoginUserDTOConverter = new CommentFeedbackNewsLoginUserDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public UserDTO save(UserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = (User) userConverter.toEntity(userDTO);
            user.getProfile().setUser(user);
            Role role = roleDao.findByName(user.getRole().getName());
            user.setRole(role);
            userDao.create(user);
            transaction.commit();
            return (UserDTO) userDTOConverter.toDto(user);
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save user", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDTO> findAll() {
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
    public UserDTO update(UserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findByEmail(userDTO.getEmail());
            if (userDTO.getFirstName() != null) {
                user.setFirstName(userDTO.getFirstName());
            }
            if (userDTO.getLastName() != null) {
                user.setLastName(userDTO.getLastName());
            }
            if (userDTO.getPassword() != null) {
                user.setPassword(userDTO.getPassword());
            }
            if (userDTO.getProfile() != null) {
                if (userDTO.getProfile().getAddress() != null) {
                    user.getProfile().setAddress(userDTO.getProfile().getAddress());
                }
                if (userDTO.getProfile().getPhoneNumber() != null) {
                    user.getProfile().setPhoneNumber(userDTO.getProfile().getPhoneNumber());
                }
            }
            if (userDTO.getRole() != null) {
                Role role = roleDao.findByName(userDTO.getRole().getName());
                user.setRole(role);
            }
            userDao.update(user);
            UserDTO updatedUserDTO = (UserDTO) userDTOConverter.toDto(user);
            transaction.commit();
            return updatedUserDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update user", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public CommentFeedbackNewsLoginUserDTO findByEmail(CommentFeedbackNewsLoginUserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findByEmail(userDTO.getEmail());
            CommentFeedbackNewsLoginUserDTO foundUserDTO = (CommentFeedbackNewsLoginUserDTO) commentFeedbackNewsLoginUserDTOConverter.toDto(user);
            transaction.commit();
            return foundUserDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve user by email", e);
        }
        return null;
    }
}
