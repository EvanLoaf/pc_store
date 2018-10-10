package com.gmail.evanloafakahaitao.pcstore.controller.filter;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class AppAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LogManager.getLogger(AppAuthenticationSuccessHandler.class);
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final PageProperties pageProperties;

    @Autowired
    public AppAuthenticationSuccessHandler(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    private void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to {}", targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    //TODO here will be way more roles, authorities are placeholders
    private String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isSecurityAdmin = false;
        boolean isSalesAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("user_basic_permission")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("security_admin_basic_permission")) {
                isSecurityAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("sales_admin_basic_permission")) {
                isSalesAdmin = true;
                break;
            }
        }
        if (isUser) {
            return pageProperties.getItemsPagePath();
        } else if (isSecurityAdmin) {
            return pageProperties.getUsersPagePath();
        } else if (isSalesAdmin) {
            return pageProperties.getItemsPagePath();
        } else {
            throw new IllegalStateException();
        }
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
