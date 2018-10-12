package com.gmail.evanloafakahaitao.pcstore.service.util;

import com.gmail.evanloafakahaitao.pcstore.service.model.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class CurrentUserExtractor {

    private static final Logger logger = LogManager.getLogger(CurrentUserExtractor.class);

    private CurrentUserExtractor() {
    }

    private static UserPrincipal getCurrentUser() {
        logger.info("Extracting and returning Current User from context");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    public static Collection<? extends GrantedAuthority> getCurrentAuthorities() {
        logger.info("Extracting and returning Current User Authorities from context");
        return getCurrentUser().getAuthorities();
    }

    public static Long getCurrentId() {
        logger.info("Extracting and returning Current User Id from context");
        return getCurrentUser().getId();
    }
}
