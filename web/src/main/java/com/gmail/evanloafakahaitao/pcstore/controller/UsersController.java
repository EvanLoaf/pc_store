package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.model.Pagination;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.controller.util.TargetDeterminer;
import com.gmail.evanloafakahaitao.pcstore.service.DiscountService;
import com.gmail.evanloafakahaitao.pcstore.service.RoleService;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.RoleDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.UserValidator;
import com.gmail.evanloafakahaitao.pcstore.service.impl.RoleServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(UsersController.class);

    private final PageProperties pageProperties;
    private final UserService userService;
    private final UserValidator userValidator;
    private final TargetDeterminer targetDeterminer;
    private final RoleService roleService;
    private final PaginationUtil paginationUtil;
    private final DiscountService discountService;

    @Autowired
    public UsersController(
            PageProperties pageProperties,
            UserService userService,
            UserValidator userValidator,
            TargetDeterminer targetDeterminer,
            RoleService roleService,
            PaginationUtil paginationUtil,
            DiscountService discountService
    ) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
        this.targetDeterminer = targetDeterminer;
        this.roleService = roleService;
        this.paginationUtil = paginationUtil;
        this.discountService = discountService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_users_all')")
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        List<UserDTO> users = userService.findAllNotDeleted(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("users", users);
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(userService.countAll().intValue())
        );
        pagination.setStartPosition(paginationUtil.getStartPosition(page) + 1);
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getUsersPagePath();
    }

    @PostMapping
    public String registerUser(
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getRegisterPagePath();
        } else {
            userService.save(user);
            return pageProperties.getLoginPagePath();
        }
    }

    /*@PostMapping(value = "/admin")
    @PreAuthorize("hasAuthority('create_user')")
    public String createUserAdmin(
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getUserCreatePagePath();
        } else {
            userService.save(user);
            return "redirect:/web/users";
        }
    }*/

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('view_user_self', 'update_user_self')")
    public String getUser(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        UserDTO user = new UserDTO();
        user.setId(id);
        UserDTO userRetrieved = userService.findById(user);
        modelMap.addAttribute("user", userRetrieved);
        /*modelMap.addAttribute("user", new UserDTO());*/
        return pageProperties.getUserProfilePagePath();
    }

    /*List<RoleDTO> roles = roleService.findAll(0, pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("roles", roles);*/

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('update_user_self')")
    public String updateUser(
            @PathVariable("id") Long id,
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        user.setId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getUserProfilePagePath();
        } else {
            userService.update(user);
            return "redirect:/web/users/" + id + "?update=true";
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
        user.setId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getUserUpdatePagePath();
        } else {
            userService.update(user);
            return "redirect:/web/users" + "?update=true";
        }
    }

    /*@GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('create_user')")
    public String createUserPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getUserCreatePagePath();
    }*/

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
        return "redirect:/web/users";
    }

    @GetMapping(value = "/{id}/disable")
    @PreAuthorize("hasAuthority('disable_user')")
    public String disableUser(
            @PathVariable("id") Long id,
            @RequestParam(value = "disable", defaultValue = "true") boolean disable
    ) {
        UserDTO user = new UserDTO();
        user.setId(id);
        user.setDisabled(disable);
        userService.update(user);
        return "redirect:/web/users";
    }

    @GetMapping(value = "/{id}/update")
    @PreAuthorize("hasAuthority('update_users_all')")
    public String updateUserPage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        UserDTO user = userService.findById(userDTO);
        List<RoleDTO> roles = roleService.findAll();
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserUpdatePagePath();
    }

    @PostMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_users')")
    public String updateUsersDiscounts(
            @RequestParam("discountId") Long discountId
    ) {
        userService.updateDiscountAll(discountId);
        return "redirect:/web/items" + "?userdiscounts=true";
    }

    @GetMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_users')")
    public String updateUsersDiscountsPage(
            ModelMap modelMap
    ) {
        List<DiscountDTO> discounts = discountService.findAll();
        modelMap.addAttribute("discounts", discounts);
        return pageProperties.getUsersSetDiscountPagePath();
    }
}
