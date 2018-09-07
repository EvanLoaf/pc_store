package com.gmail.evanloafakahaitao.dao.util;

import com.gmail.evanloafakahaitao.dao.model.Role;
import com.gmail.evanloafakahaitao.dao.model.RoleEnum;
import com.gmail.evanloafakahaitao.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserConverter {

    private static final Logger logger = LogManager.getLogger(UserConverter.class);

    public User toUser(ResultSet resultSet) {
        Long id = null;
        String email = null;
        String password = null;
        String firstName = null;
        String lastName = null;
        Role role = null;
        try {
            id = resultSet.getLong("id");
            email = resultSet.getString("email");
            password = resultSet.getString("password");
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            role = Role.newBuilder().withId(resultSet.getLong("f_role_id")).withName(RoleEnum.USER).build();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return User.newBuilder()
                .withId(id)
                .withEmail(email)
                .withPassword(password)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withRole(role)
                .build();
    }
}
