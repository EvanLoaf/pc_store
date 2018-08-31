package com.gmail.evanloafakahaitao.filter;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.model.RoleEnum;
import com.gmail.evanloafakahaitao.model.AccessMode;
import com.gmail.evanloafakahaitao.model.CommandEnum;
import com.gmail.evanloafakahaitao.model.RequestEnum;
import com.gmail.evanloafakahaitao.model.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);

    private static final ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private static final Set<AccessMode> ADMIN_AVAILABLE = new HashSet<>();
    private static final Set<AccessMode> USER_AVAILABLE = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Authentication Filter Initialized");

        /**
         * Access mode - combination of parameters
         * In order to add additional protection
         * Depending on your role, request and request type
         * You might or might not be granted access
         */

        // **********USER_AVAILABLE**********
        USER_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.USER)
                        .withCommand(CommandEnum.LOGIN)
                        .withRequest(RequestEnum.POST)
                        .build()
        );
        USER_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.USER)
                        .withCommand(CommandEnum.ITEMS)
                        .withRequest(RequestEnum.GET)
                        .build()
        );
        USER_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.USER)
                        .withCommand(CommandEnum.ORDERS)
                        .withRequest(RequestEnum.GET)
                        .build()
        );
        USER_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.USER)
                        .withCommand(CommandEnum.ADD_ORDER)
                        .withRequest(RequestEnum.GET)
                        .build()
        );
        USER_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.USER)
                        .withCommand(CommandEnum.CONFIRM_ORDER)
                        .withRequest(RequestEnum.POST)
                        .build()
        );
        USER_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.USER)
                        .withCommand(CommandEnum.DELETE_ORDER)
                        .withRequest(RequestEnum.GET)
                        .build()
        );
        USER_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.USER)
                        .withCommand(CommandEnum.SEND_FEEDBACK)
                        .withRequest(RequestEnum.GET)
                        .build()
        );
        USER_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.USER)
                        .withCommand(CommandEnum.SUBMIT_FEEDBACK)
                        .withRequest(RequestEnum.POST)
                        .build()
        );


        // **********ADMIN_AVAILABLE**********
        ADMIN_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.ADMIN)
                        .withCommand(CommandEnum.LOGIN)
                        .withRequest(RequestEnum.POST)
                        .build()
        );
        ADMIN_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.ADMIN)
                        .withCommand(CommandEnum.ITEMS)
                        .withRequest(RequestEnum.GET)
                        .build()
        );
        ADMIN_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.ADMIN)
                        .withCommand(CommandEnum.USERS)
                        .withRequest(RequestEnum.GET)
                        .build()
        );
        ADMIN_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.ADMIN)
                        .withCommand(CommandEnum.LOAD_ITEMS)
                        .withRequest(RequestEnum.GET)
                        .build()
        );
        ADMIN_AVAILABLE.add(
                AccessMode.newBuilder()
                        .withRole(RoleEnum.ADMIN)
                        .withCommand(CommandEnum.SHOW_FEEDBACK)
                        .withRequest(RequestEnum.GET)
                        .build()
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        String command = req.getParameter("command");
        String request = req.getMethod();
        if (session == null) {
            defaultRequest(servletRequest, servletResponse, filterChain, req, resp, command);
        } else {
            UserPrincipal userPrincipal = (UserPrincipal) session.getAttribute("user");
            if (userPrincipal == null) {
                defaultRequest(servletRequest, servletResponse, filterChain, req, resp, command);
            } else {
                CommandEnum commandEnum = CommandEnum.getCommand(command);
                RoleEnum roleEnum = userPrincipal.getRole();
                RequestEnum requestEnum = RequestEnum.getRequest(request);
                AccessMode accessMode = AccessMode.newBuilder()
                        .withRole(roleEnum)
                        .withCommand(commandEnum)
                        .withRequest(requestEnum)
                        .build();
                if (!USER_AVAILABLE.contains(accessMode) && !ADMIN_AVAILABLE.contains(accessMode)) {
                    session.removeAttribute("user");
                    resp.sendRedirect(req.getContextPath() + configurationManager.getProperty(PageProperties.LOGIN_PAGE_PATH));
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }
    }

    private void defaultRequest(ServletRequest request, ServletResponse response, FilterChain chain, HttpServletRequest req, HttpServletResponse resp, String command) throws IOException, ServletException {
        if (req.getMethod().equals("POST")) {
            if (CommandEnum.getCommand(command) == CommandEnum.LOGIN) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(req.getContextPath() + configurationManager.getProperty(PageProperties.LOGIN_PAGE_PATH));
            }
        } else {
            resp.sendRedirect(req.getContextPath() + configurationManager.getProperty(PageProperties.LOGIN_PAGE_PATH));
        }
    }

    @Override
    public void destroy() {
        logger.debug("Authentication Filter Destroyed");
    }
}
