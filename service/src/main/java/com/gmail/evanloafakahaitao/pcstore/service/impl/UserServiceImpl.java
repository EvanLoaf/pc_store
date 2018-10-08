package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.DiscountDao;
import com.gmail.evanloafakahaitao.pcstore.dao.RoleDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.DiscountDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.RoleDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.SimpleUserDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.UserConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.UserDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ProfileDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final DiscountDao discountDao;
    private final DTOConverter<UserDTO, User> userDTOConverter;
    private final Converter<UserDTO, User> userConverter;
    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter;
    private final Converter<ProfileDTO, Profile> profileConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(
            UserDao userDao,
            RoleDao roleDao,
            DiscountDao discountDao,
            @Qualifier("userDTOConverter") DTOConverter<UserDTO, User> userDTOConverter,
            @Qualifier("userConverter") Converter<UserDTO, User> userConverter,
            @Qualifier("simpleUserDTOConverter") DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter,
            @Qualifier("profileConverter") Converter<ProfileDTO, Profile> profileConverter,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.discountDao = discountDao;
        this.userDTOConverter = userDTOConverter;
        this.userConverter = userConverter;
        this.simpleUserDTOConverter = simpleUserDTOConverter;
        this.profileConverter = profileConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        logger.info("Saving User");
        if (userDTO.getRole() != null) {
            Role role = roleDao.findByName(userDTO.getRole().getName());
            User user = userConverter.toEntity(userDTO);
            user.setDisabled(false);
            user.setRole(role);
            user.setPassword(
                    bCryptPasswordEncoder.encode(userDTO.getPassword())
            );
            if (userDTO.getDiscount() != null && userDTO.getDiscount().getPercent() != null) {
                Discount discount = discountDao.findByPercent(userDTO.getDiscount().getPercent());
                user.setDiscount(discount);
            } else {
                user.setDiscount(null);
            }
            if (userDTO.getProfile() != null) {
                Profile profile = profileConverter.toEntity(userDTO.getProfile());
                user.setProfile(profile);
                profile.setUser(user);
            } else {
                Profile profile = new Profile();
                user.setProfile(profile);
                profile.setUser(user);
            }
            userDao.create(user);
            return userDTOConverter.toDto(user);
        } else {
            return null;
        }
    }

    @Override
    public List<UserDTO> findAll(Integer startPosition, Integer maxResults) {
        logger.info("Retrieving all Users");
        List<User> users = userDao.findAll(startPosition, maxResults);
        return userDTOConverter.toDTOList(users);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        logger.info("Updating User");
        User user = userDao.findByEmail(userDTO.getEmail());
        if (userDTO.isDisabled() != null) {
            user.setDisabled(userDTO.isDisabled());
            userDao.update(user);
        } else {
            if (userDTO.getFirstName() != null) {
                user.setFirstName(userDTO.getFirstName());
            }
            if (userDTO.getLastName() != null) {
                user.setLastName(userDTO.getLastName());
            }
            if (userDTO.getPassword() != null) {
                user.setPassword(
                        bCryptPasswordEncoder.encode(userDTO.getPassword())
                );
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
        }
        return userDTOConverter.toDto(user);
    }

    @Override
    public SimpleUserDTO findByEmail(SimpleUserDTO userDTO) {
        logger.info("Retrieving User by Email");
        User user = userDao.findByEmail(userDTO.getEmail());
        return simpleUserDTOConverter.toDto(user);
    }
}
