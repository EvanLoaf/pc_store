package com.gmail.evanloafakahaitao.util;

import com.gmail.evanloafakahaitao.model.RoleEnum;
import com.gmail.evanloafakahaitao.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserConverter {

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
            System.out.println("Error extracting values from result set into UserConverter");
            e.printStackTrace();
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
