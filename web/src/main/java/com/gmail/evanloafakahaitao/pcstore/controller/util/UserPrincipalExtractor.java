package com.gmail.evanloafakahaitao.pcstore.controller.util;

import com.gmail.evanloafakahaitao.pcstore.service.model.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserPrincipalExtractor {

    private static final Logger logger = LogManager.getLogger(UserPrincipalExtractor.class);

    public UserPrincipal getUserPrincipal() {
        logger.info("Extracting and returning User Principal from context");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        logger.info("Extracting and returning User Principal Authorities from context");
        return getUserPrincipal().getAuthorities();
    }
}
