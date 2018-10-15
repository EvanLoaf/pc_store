package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class ApplicationController {

    private static final Logger logger = LogManager.getLogger(UsersController.class);

    private final PageProperties pageProperties;

    @Autowired
    public ApplicationController(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
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


}
