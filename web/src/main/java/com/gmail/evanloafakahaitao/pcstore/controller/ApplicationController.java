package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class ApplicationController {

    private final PageProperties pageProperties;

    @Autowired
    public ApplicationController(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    @GetMapping(value = "/login")
    public String loginPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getLoginPagePath();
    }

    @GetMapping
    public String registerPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getRegisterPagePath();
    }
}
