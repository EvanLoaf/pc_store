package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final PageProperties pageProperties;
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public UsersController(
            PageProperties pageProperties,
            UserService userService,
            UserValidator userValidator
    ) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String getUsers(ModelMap modelMap) {
        List<UserDTO> users = userService.findAll(0, pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }

    @PostMapping
    public String createUser(
            @ModelAttribute UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return pageProperties.getRegisterPagePath();
        } else {
            user = userService.save(user);
            modelMap.addAttribute("user", user);
            return "redirect:/users";
        }
    }
}
