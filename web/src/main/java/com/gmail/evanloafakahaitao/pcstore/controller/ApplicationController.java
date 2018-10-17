package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.WebProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.UserValidator;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX)
public class ApplicationController {

    private static final Logger logger = LogManager.getLogger(UsersController.class);

    private final PageProperties pageProperties;
    private final UserValidator userValidator;
    private final UserService userService;

    @Autowired
    public ApplicationController(
            PageProperties pageProperties,
            UserValidator userValidator,
            UserService userService
    ) {
        this.pageProperties = pageProperties;
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String loginPage(ModelMap modelMap) {
        logger.debug("Executing Application Controller method : loginPage");
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getLoginPagePath();
    }

    @GetMapping(value = "/register")
    public String registerPage(ModelMap modelMap) {
        logger.debug("Executing Application Controller method : registerPage");
        logger.info("Entered register page");
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getRegisterPagePath();
    }

    @PostMapping(value = "/register/users/create")
    public String registerUser(
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Application Controller method : registerUser");
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getRegisterPagePath();
        } else {
            userService.save(user);
            return pageProperties.getLoginPagePath();
        }
    }
}
