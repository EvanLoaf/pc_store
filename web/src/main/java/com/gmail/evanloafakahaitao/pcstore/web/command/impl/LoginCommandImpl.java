package com.gmail.evanloafakahaitao.pcstore.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.impl.UserServiceImpl;
import com.gmail.evanloafakahaitao.pcstore.web.command.Command;
import com.gmail.evanloafakahaitao.pcstore.web.util.LoginValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
