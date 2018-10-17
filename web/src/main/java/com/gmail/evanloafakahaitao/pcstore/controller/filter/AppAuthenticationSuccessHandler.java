package com.gmail.evanloafakahaitao.pcstore.controller.filter;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.dao.model.PermissionEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
import java.util.Objects;

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
            Authentication authentication
    ) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to {}", targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isSecurityAdmin = false;
        boolean isSalesAdmin = false;
        boolean isApiAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (Objects.requireNonNull(PermissionEnum.getPermission(grantedAuthority.getAuthority()))) {
                case USER_BASIC_PERMISSION:
                    isUser = true;
                    break;
                case SECURITY_ADMIN_BASIC_PERMISSION:
                    isSecurityAdmin = true;
                    break;
                case SALES_ADMIN_BASIC_PERMISSION:
                    isSalesAdmin = true;
                    break;
                case API_ADMIN_BASIC_PERMISSION:
                    isApiAdmin = true;
                    break;
            }
        }
        if (isUser) {
            return pageProperties.getItemsPagePath();
        } else if (isSecurityAdmin) {
            return pageProperties.getUsersPagePath();
        } else if (isSalesAdmin) {
            return pageProperties.getItemsPagePath();
        } else if (isApiAdmin) {
            throw new AccessDeniedException("API admin is not allowed to access UI");
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
