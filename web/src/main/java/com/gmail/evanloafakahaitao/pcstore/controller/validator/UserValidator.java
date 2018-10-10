package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors err) {
        UserDTO user = (UserDTO) obj;
        if (user.getId() == null) {
            ValidationUtils.rejectIfEmpty(err, "email", "user.email.empty");
            ValidationUtils.rejectIfEmpty(err, "password", "user.password.empty");
            ValidationUtils.rejectIfEmpty(err, "firstName", "user.firstname.empty");
            ValidationUtils.rejectIfEmpty(err, "lastName", "user.lastname.empty");

            Pattern emailPattern = Pattern.compile(
                    "^[A-Z0-9._%+-]+@[A-Z0-9]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE
            );
            if (!(emailPattern.matcher(user.getEmail()).matches())) {
                err.rejectValue("email", "user.email.invalid");
            }
            if (user.getEmail().length() > 30) {
                err.rejectValue("email", "user.email.length");
            }
            Pattern passwordPattern = Pattern.compile(
                    "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
                    Pattern.CASE_INSENSITIVE
            );
            if (!(passwordPattern.matcher(user.getPassword()).matches())) {
                err.rejectValue("password", "user.password.invalid");
            }
            if (user.getLastName().length() > 20) {
                err.rejectValue("lastName", "user.lastname.length");
            }
            if (user.getFirstName().length() > 20) {
                err.rejectValue("firstName", "user.firstname.length");
            }
            if (user.getEmail() != null && user.getEmail().length() <= 30) {
                SimpleUserDTO userDTO = new SimpleUserDTO();
                userDTO.setEmail(user.getEmail());
                SimpleUserDTO userByEmail = userService.findByEmail(userDTO);
                if (userByEmail.getId() != null) {
                    err.rejectValue("email", "user.email.exists");
                }
            }

        } else {
            if (user.getPassword() != null) {
                Pattern passwordPattern = Pattern.compile(
                        "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
                        Pattern.CASE_INSENSITIVE
                );
                if (!(passwordPattern.matcher(user.getPassword()).matches())) {
                    err.rejectValue("password", "user.password.invalid");
                }
            }
            if (user.getLastName() != null && user.getLastName().length() > 20) {
                err.rejectValue("lastName", "user.lastname.length");
            }
            if (user.getFirstName() != null && user.getFirstName().length() > 20) {
                err.rejectValue("firstName", "user.firstname.length");
            }
            if (user.getProfile() != null) {
                if (user.getProfile().getAddress() != null && user.getProfile().getAddress().length() > 90) {
                    err.rejectValue("profile.address", "user.address.length");
                }
                if (user.getProfile().getPhoneNumber() != null && user.getProfile().getPhoneNumber().length() > 20) {
                    err.rejectValue("profile.phoneNumber", "user.phonenumber.length");
                }
            }
        }
    }
}
