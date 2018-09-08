package com.gmail.evanloafakahaitao.web.command.impl;

import com.gmail.evanloafakahaitao.service.ItemService;
import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.service.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.web.model.RoleEnum;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.UserService;
import com.gmail.evanloafakahaitao.service.impl.UserServiceImpl;
import com.gmail.evanloafakahaitao.web.command.Command;
import com.gmail.evanloafakahaitao.web.model.CommandEnum;
import com.gmail.evanloafakahaitao.web.util.LoginValidator;
import com.gmail.evanloafakahaitao.web.util.UserPrincipalConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Random;

public class LoginCommandImpl implements Command {

    private static final Logger logger = LogManager.getLogger(LoginCommandImpl.class);
    private ItemService itemService = new ItemServiceImpl();

    private UserService userService = new UserServiceImpl();
    private LoginValidator loginValidator = new LoginValidator();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
        Hibernate test method
         */
        ItemDTO itemDTO = ItemDTO.newBuilder()
                .withDescription("PC Added with Hibernate")
                .withName("Hibernate PC")
                .withPrice(BigDecimal.valueOf(111.11))
                .withVendorCode((long) new Random().nextInt(99999999))
                .build();
        ItemDTO itemDTOSaved = itemService.save(itemDTO);
        logger.info(
                String.format("DTO Item Saved: id %d name %s desc %s price %.2f v_code %s",
                        itemDTOSaved.getId(), itemDTOSaved.getName(), itemDTOSaved.getDescription(),
                        itemDTOSaved.getPrice(), itemDTOSaved.getVendorCode())
        );
        /*
        Temporary code
         */
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        boolean loginSuccess = loginValidator.validate(email, password);
        if (loginSuccess) {
            User user = userService.findByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("user", UserPrincipalConverter.toUserPrincipal(user));
            if (user.getRole() == RoleEnum.ADMIN) {
                response.sendRedirect(request.getContextPath() + CommandEnum.USERS.getUrl());
            } else {
                response.sendRedirect(request.getContextPath() + CommandEnum.ITEMS.getUrl());
            }
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            return configurationManager.getProperty(PageProperties.LOGIN_PAGE_PATH);
        }
        return null;
    }
}
