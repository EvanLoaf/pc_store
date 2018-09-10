package com.gmail.evanloafakahaitao.web.command.impl;

import com.gmail.evanloafakahaitao.service.ItemService;
import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.service.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.web.model.RoleEnum;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.UserService;
import com.gmail.evanloafakahaitao.service.impl.UserServiceImpl;
import com.gmail.evanloafakahaitao.web.command.Command;
import com.gmail.evanloafakahaitao.web.model.CommandEnum;
import com.gmail.evanloafakahaitao.web.util.LoginValidator;
import com.gmail.evanloafakahaitao.web.util.UserPrincipalConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommandImpl implements Command {

    private UserService userService = new UserServiceImpl();
    private LoginValidator loginValidator = new LoginValidator();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        boolean loginSuccess = loginValidator.validate(email, password);
        /*if (loginSuccess) {
            User user = userService.findByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("user", UserPrincipalConverter.toUserPrincipal(user));
            if (user.getRole().getName().equals(RoleEnum.ADMIN.toString())) {
                response.sendRedirect(request.getContextPath() + CommandEnum.USERS.getUrl());
            } else {
                response.sendRedirect(request.getContextPath() + CommandEnum.ITEMS.getUrl());
            }
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            return configurationManager.getProperty(PageProperties.LOGIN_PAGE_PATH);
        }*/
        return null;
    }
}
