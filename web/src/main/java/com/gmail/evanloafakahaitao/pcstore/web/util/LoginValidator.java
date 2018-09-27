package com.gmail.evanloafakahaitao.pcstore.web.util;

import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator {

    @Autowired
    private UserService userService;

    public boolean validate(String email, String password) {
        if (email != null && !email.equals("") && password != null && !password.equals("")) {
            if (email.trim().length() <= 30 && password.trim().length() <= 20) {
                /*User userByEmail = userService.findByEmail(email.trim());
                if (userByEmail != null) {
                    if (userByEmail.getPassword().equals(password.trim())) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }*/
            } else {
                return false;
            }
        } else {
            return false;
        }



        // TEMPORARY DELETE AFTER !!!!!!!!!!!!
        return true;
    }
}
