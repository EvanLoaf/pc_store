package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.util.TargetDeterminer;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/users")
public class UsersController {

    private final PageProperties pageProperties;
    private final UserService userService;
    private final UserValidator userValidator;
    private final TargetDeterminer targetDeterminer;

    @Autowired
    public UsersController(
            PageProperties pageProperties,
            UserService userService,
            UserValidator userValidator, TargetDeterminer targetDeterminer) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
        this.targetDeterminer = targetDeterminer;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_users_all')")
    public String getUsers(
            @RequestParam("page") Integer page,
            ModelMap modelMap
    ) {
        if (page == null) {
            page = 1;
        }
        int startPosition = (page - 1) * pageProperties.getPaginationMaxResults();
        List<UserDTO> users = userService.findAll(startPosition, pageProperties.getPaginationMaxResults());
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
            modelMap.addAttribute("user", user);
        } else {
            userService.save(user);
        }
        return targetDeterminer.urlAfterUserCreation(result.hasErrors());
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('view_user_self', 'update_user_self', 'update_users_all')")
    public String getUser(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        UserDTO user = new UserDTO();
        user.setId(id);
        UserDTO userRetrieved = userService.findById(user);
        modelMap.addAttribute("viewuser", userRetrieved);
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getUserUpdatePagePath();
    }

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('update_user_self', 'update_users_all')")
    public String updateUser(
            @PathVariable("id") Long id,
            @ModelAttribute UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        user.setId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
        } else {
            userService.update(user);
        }
        return pageProperties.getUserUpdatePagePath();
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('create_user')")
    public String createUserPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getUserCreatePagePath();
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('delete_user')")
    public String deleteUser(
            @RequestParam("ids") Long[] ids
    ) {
        for (Long id : ids) {
            SimpleUserDTO user = new SimpleUserDTO();
            user.setId(id);
            userService.deleteById(user);
        }
        return pageProperties.getUsersPagePath();
    }

    @GetMapping(value = "/{id}/disable")
    @PreAuthorize("hasAuthority('disable_user')")
    public String disableUser(
            @PathVariable("id") Long id,
            @RequestParam("disable") boolean disable
    ) {
        UserDTO user = new UserDTO();
        user.setId(id);
        user.setDisabled(disable);
        userService.update(user);
        return pageProperties.getUsersPagePath();
    }
}
