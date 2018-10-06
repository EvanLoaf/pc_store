package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final PageProperties pageProperties;

    @Autowired
    public RegisterController(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    @GetMapping
    public String getRegisterPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getRegisterPagePath();
    }
}
