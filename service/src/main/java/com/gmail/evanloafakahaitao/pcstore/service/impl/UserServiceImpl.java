package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.DiscountDao;
import com.gmail.evanloafakahaitao.pcstore.dao.RoleDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.dao.model.PermissionEnum;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.util.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
public class UserServiceImpl implements  UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final DiscountDao discountDao;
    private final DTOConverter<UserDTO, User> userDTOConverter;
    private final Converter<UserDTO, User> userConverter;
    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter;
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(
            UserDao userDao,
            RoleDao roleDao,
            DiscountDao discountDao,
            @Qualifier("userDTOConverter") DTOConverter<UserDTO, User> userDTOConverter,
            @Qualifier("userConverter") Converter<UserDTO, User> userConverter,
            @Qualifier("simpleUserDTOConverter") DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter
    ) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.discountDao = discountDao;
        this.userDTOConverter = userDTOConverter;
        this.userConverter = userConverter;
        this.simpleUserDTOConverter = simpleUserDTOConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.discountDTOConverter = discountDTOConverter;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        logger.info("Saving User");
        //TODO isDefault impl
        Role role = roleDao.findByName("user");
        User user = userConverter.toEntity(userDTO);
        user.setDisabled(false);
        user.setDeleted(false);
        user.setRole(role);
        user.setPassword(
                bCryptPasswordEncoder.encode(userDTO.getPassword())
        );
        user.getProfile().setUser(user);
        userDao.create(user);
        return userDTOConverter.toDto(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        logger.info("Updating User");
        //TODO test
        if (CurrentUserUtil.getCurrentAuthorities().contains(PermissionEnum.USER_BASIC_PERMISSION.toString())) {
            if (!userDTO.getId().equals(CurrentUserUtil.getCurrentId())) {
                throw new IllegalStateException("User is only allowed to update himself");
            }
        }
        User user = userDao.findOne(userDTO.getId());
        if (userDTO.getDisabled() != null) {
            user.setDisabled(userDTO.getDisabled());
            userDao.update(user);
        } else {
            if (userDTO.getFirstName() != null) {
                user.setFirstName(userDTO.getFirstName());
            }
            if (userDTO.getLastName() != null) {
                user.setLastName(userDTO.getLastName());
            }
            if (userDTO.getPassword() != null && !userDTO.getPassword().equals("")) {
                user.setPassword(
                        bCryptPasswordEncoder.encode(userDTO.getPassword())
                );
            }
            if (userDTO.getAddress() != null) {
                user.getProfile().setAddress(userDTO.getAddress());
            }
            if (userDTO.getPhoneNumber() != null) {
                user.getProfile().setPhoneNumber(userDTO.getPhoneNumber());
            }
            if (userDTO.getRole() != null) {
                Role role = roleDao.findOne(userDTO.getRole().getId());
                user.setRole(role);
            }
            userDao.update(user);
        }
        return userDTOConverter.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleUserDTO findByEmail(String email) {
        logger.info("Retrieving User by Email");
        User user = userDao.findByEmail(email);
        if (user != null) {
            return simpleUserDTOConverter.toDto(user);
        } else {
            //TODO not found?
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        logger.info("Retrieving User by Id");
        User user = userDao.findOne(id);
        //TODO not found?
        return userDTOConverter.toDto(user);
    }

    @Override
    public UserDTO findByCurrentId() {
        logger.info("Retrieving User by Current Id");
        User user = userDao.findOne(
                CurrentUserUtil.getCurrentId()
        );
        return userDTOConverter.toDto(user);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting User by Id");
        userDao.deleteById(id);
    }

    @Override
    public Long countAll() {
        logger.info("Counting all Users");
        return userDao.countAll();
    }

    @Override
    public List<UserDTO> findAllNotDeleted(Integer startPosition, Integer maxResults) {
        logger.info("Retrieving all not deleted Users");
        List<User> users = userDao.findAllNotDeleted(startPosition, maxResults);
        return userDTOConverter.toDTOList(users);
    }

    @Override
    public DiscountDTO updateDiscountAll(Long discountId) {
        Discount discount = discountDao.findOne(discountId);
        List<User> users = userDao.findAll();
        for (User user : users) {
            user.setDiscount(discount);
            userDao.update(user);
        }
        return discountDTOConverter.toDto(discount);
    }
}
