package com.gmail.evanloafakahaitao.pcstore.controller.util;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TargetDeterminer {

    private final UserPrincipalExtractor userPrincipalExtractor;
    private final PageProperties pageProperties;

    @Autowired
    public TargetDeterminer(UserPrincipalExtractor userPrincipalExtractor, PageProperties pageProperties) {
        this.userPrincipalExtractor = userPrincipalExtractor;
        this.pageProperties = pageProperties;
    }

    public String urlAfterUserCreation(boolean hasErrors) {
        boolean isUser = false;
        boolean isSecurityAdmin = false;
        Collection<? extends GrantedAuthority> authorities = userPrincipalExtractor.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("user_basic_permission")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("security_admin_basic_permission")) {
                isSecurityAdmin = true;
                break;
            }
        }
        if (hasErrors) {
            if (isUser) {
                return pageProperties.getRegisterPagePath();
            } else if (isSecurityAdmin) {
                return pageProperties.getUserCreatePagePath();
            } else {
                throw new IllegalStateException();
            }
        } else {
            if (isUser) {
                return pageProperties.getLoginPagePath();
            } else if (isSecurityAdmin) {
                return pageProperties.getUsersPagePath();
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public String methodToFindOrders() {
        Collection<? extends GrantedAuthority> authorities = userPrincipalExtractor.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("view_orders_all")) {
                return "all";
            } else if (grantedAuthority.getAuthority().equals("view_orders_self")) {
                return "self";
            }
        }
        throw new IllegalStateException();
    }
}
