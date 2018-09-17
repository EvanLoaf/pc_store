package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.DiscountDao;
import com.gmail.evanloafakahaitao.pcstore.dao.RoleDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.DiscountDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.RoleDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.SimpleUserDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.UserConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.UserDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao = new UserDaoImpl(User.class);
    private RoleDao roleDao = new RoleDaoImpl(Role.class);
    private DiscountDao discountDao = new DiscountDaoImpl(Discount.class);
    private DTOConverter userDTOConverter = new UserDTOConverter();
    private Converter userConverter = new UserConverter();
    private DTOConverter simpleUserDTOConverter = new SimpleUserDTOConverter();

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
            /*Role role = roleDao.findByName(user.getRole().getName());
            user.setRole(role);*/
            if (userDTO.getDiscount() != null && userDTO.getDiscount().getPercent() != null) {
                Discount discount = discountDao.findByPercent(userDTO.getDiscount().getPercent());
                user.setDiscount(discount);
            } else {
                user.setDiscount(null);
            }
            userDao.create(user);
            UserDTO userDTOsaved = (UserDTO) userDTOConverter.toDto(user);
            transaction.commit();
            return userDTOsaved;
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
        List<UserDTO> userDTOS = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<User> users = userDao.findAll();
            userDTOS = userDTOConverter.toDTOList(users);
            transaction.commit();
            return userDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve users", e);
        }
        return userDTOS;
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
            if (userDTO.getDiscount() != null && userDTO.getDiscount().getPercent() != null) {
                Discount discount = discountDao.findByPercent(userDTO.getDiscount().getPercent());
                user.setDiscount(discount);
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
    public SimpleUserDTO findByEmail(SimpleUserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findByEmail(userDTO.getEmail());
            SimpleUserDTO foundUserDTO = (SimpleUserDTO) simpleUserDTOConverter.toDto(user);
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
