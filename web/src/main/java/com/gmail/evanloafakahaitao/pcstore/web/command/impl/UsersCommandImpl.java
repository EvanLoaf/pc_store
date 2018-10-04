package com.gmail.evanloafakahaitao.pcstore.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.impl.UserServiceImpl;
import com.gmail.evanloafakahaitao.pcstore.web.command.Command;
import com.gmail.evanloafakahaitao.pcstore.web.properties.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UsersCommandImpl implements Command {

    @Autowired
    private UserService userService;
    @Autowired
    private PageProperties pageProperties;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*List<User> users = userService.findAll();
        if (users != null) {
            request.setAttribute("users", users);
        } else {
            request.setAttribute("error", "Error retrieving users");
        }*/
        return pageProperties.getUsersPagePath();
    }
}
