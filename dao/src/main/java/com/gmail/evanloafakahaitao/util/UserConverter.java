package com.gmail.evanloafakahaitao.util;

import com.gmail.evanloafakahaitao.model.RoleEnum;
import com.gmail.evanloafakahaitao.model.User;
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
        String phoneNumber = null;
        String addInfo = null;
        RoleEnum role = null;
        try {
            id = resultSet.getLong("id");
            email = resultSet.getString("email");
            password = resultSet.getString("password");
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            phoneNumber = resultSet.getString("phone_number");
            addInfo = resultSet.getString("add_info");
            role = RoleEnum.getRole(resultSet.getString("role"));
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return User.newBuilder()
                .withId(id)
                .withEmail(email)
                .withPassword(password)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withPhoneNumber(phoneNumber)
                .withAddInfo(addInfo)
                .withRole(role)
                .build();
    }
}
