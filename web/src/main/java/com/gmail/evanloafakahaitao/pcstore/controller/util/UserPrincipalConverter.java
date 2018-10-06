package com.gmail.evanloafakahaitao.pcstore.controller.util;

import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.controller.model.UserPrincipal;

public class UserPrincipalConverter {

    public static UserPrincipal toUserPrincipal(User user) {
        return UserPrincipal.newBuilder()
                .withId(user.getId())
                .withEmail(user.getEmail())
                .withName(user.getFirstName() + " " + user.getLastName())
                /*.withRole(user.getRole())*/
                .build();
    }
}
