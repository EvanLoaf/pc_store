package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.model.Pagination;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.WebProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.FieldTrimmer;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.service.DiscountService;
import com.gmail.evanloafakahaitao.pcstore.service.RoleService;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.RoleDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users")
public class UsersController {

    private static final Logger logger = LogManager.getLogger(UsersController.class);

    private final PageProperties pageProperties;
    private final UserService userService;
    private final RoleService roleService;
    private final PaginationUtil paginationUtil;
    private final DiscountService discountService;
    private final Validator userValidator;
    private final FieldTrimmer fieldTrimmer;

    @Autowired
    public UsersController(
            PageProperties pageProperties,
            UserService userService,
            RoleService roleService,
            PaginationUtil paginationUtil,
            DiscountService discountService,
            @Qualifier("userValidator") Validator userValidator,
            FieldTrimmer fieldTrimmer
    ) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.roleService = roleService;
        this.paginationUtil = paginationUtil;
        this.discountService = discountService;
        this.userValidator = userValidator;
        this.fieldTrimmer = fieldTrimmer;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_users_all')")
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method : getUsers page " + page );
        List<UserDTO> users = userService.findAllNotDeleted(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("users", users);
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(userService.countAll().intValue())
        );
        pagination.setStartPosition(paginationUtil.getPageNumerationStart(page));
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getUsersPagePath();
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("hasAnyAuthority('view_user_self', 'update_user_self')")
    public String getUser(
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method : getUser - Profile");
        UserDTO user = userService.findByCurrentId();
        modelMap.addAttribute("user", user);
        return pageProperties.getUserProfilePagePath();
    }

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('update_user_self')")
    public String updateUser(
            @PathVariable("id") Long id,
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method : updateUser with id " + id);
        user = fieldTrimmer.trim(user);
        user.setId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getUserProfilePagePath();
        } else {
            userService.update(user);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users/profile" + "?update=true";
        }
    }

    @PostMapping(value = "/{id}/admin")
    @PreAuthorize("hasAuthority('update_users_all')")
    public String updateUserByAdmin(
            @PathVariable("id") Long id,
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method : updateUserByAdmin with id " + id);
        user = fieldTrimmer.trim(user);
        user.setId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getUserUpdatePagePath();
        } else {
            userService.update(user);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users" + "?update=true";
        }
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('delete_user')")
    public String deleteUsers(
            @RequestParam("ids") Long[] ids
    ) {
        logger.debug("Executing Users Controller method : deleteUsers with id's " + Arrays.toString(ids));
        for (Long id : ids) {
            userService.deleteById(id);
        }
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users";
    }

    @GetMapping(value = "/{id}/disable")
    @PreAuthorize("hasAuthority('disable_user')")
    public String disableUser(
            @PathVariable("id") Long id,
            @RequestParam(value = "disable", defaultValue = "true") boolean disable
    ) {
        logger.debug("Executing Users Controller method : disableUser with id " + id);
        UserDTO user = new UserDTO();
        user.setId(id);
        user.setDisabled(disable);
        userService.update(user);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users";
    }

    @GetMapping(value = "/{id}/update")
    @PreAuthorize("hasAuthority('update_users_all')")
    public String updateUserPage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method : updateUserPage with id " + id);
        UserDTO user = userService.findById(id);
        List<RoleDTO> roles = roleService.findAll();
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserUpdatePagePath();
    }

    @PostMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_users')")
    public String updateUsersDiscount(
            @RequestParam("discountId") Long discountId
    ) {
        logger.debug("Executing Users Controller method : updateUsersDiscount with discount id " + discountId);
        userService.updateDiscountAll(discountId);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items" + "?userdiscounts=true";
    }

    @GetMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_users')")
    public String updateUsersDiscountPage(
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method : updateUsersDiscountPage");
        List<DiscountDTO> discounts = discountService.findAll();
        modelMap.addAttribute("discounts", discounts);
        return pageProperties.getUsersSetDiscountPagePath();
    }
}
