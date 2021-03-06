package com.gmail.evanloafakahaitao.util;

import com.gmail.evanloafakahaitao.model.User;
import com.gmail.evanloafakahaitao.model.UserPrincipal;

public class UserPrincipalConverter {

    public static UserPrincipal toUserPrincipal(User user) {
        return UserPrincipal.newBuilder()
                .withId(user.getId())
                .withEmail(user.getEmail())
                .withName(user.getFirstName() + " " + user.getLastName())
                .withRole(user.getRole())
                .build();
    }
}
