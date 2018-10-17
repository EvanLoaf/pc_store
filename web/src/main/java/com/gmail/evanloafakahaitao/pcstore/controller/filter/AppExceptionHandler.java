package com.gmail.evanloafakahaitao.pcstore.controller.filter;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.service.exception.NewsNotFoundException;
import com.gmail.evanloafakahaitao.pcstore.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class AppExceptionHandler {

    private final PageProperties pageProperties;

    @Autowired
    public AppExceptionHandler(
            PageProperties pageProperties
    ) {
        this.pageProperties = pageProperties;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundErrorHandler(HttpServletRequest request, UserNotFoundException e) {
        request.setAttribute("tip", "Expected User is nowhere to be found - check app data integrity");
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }

    @ExceptionHandler(NewsNotFoundException.class)
    public String newsNotFoundErrorHandler(HttpServletRequest request, NewsNotFoundException e) {
        request.setAttribute("tip", "Expected piece of News is nowhere to be found - check app data integrity");
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String userNotFoundErrorHandler(HttpServletRequest request, SQLIntegrityConstraintViolationException e) {
        request.setAttribute("tip", "Can't have an Item in two orders with current implementation of DB, if mistake caused by ordering same Item twice");
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }

    @ExceptionHandler(DisabledException.class)
    public String disabledErrorHandler(HttpServletRequest request, DisabledException e) {
        request.setAttribute("tip", "For further info regarding your account - contact administration");
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }

    @ExceptionHandler(AccountExpiredException.class)
    public String accountExpiredErrorHandler(HttpServletRequest request, AccountExpiredException e) {
        request.setAttribute("tip", "For further info regarding your account - contact administration");
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String accessErrorHandler(HttpServletRequest request, AccessDeniedException e) {
        request.setAttribute("tip", "Resource you requested does not exist");
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String badCredentialsErrorHandler(HttpServletRequest request, BadCredentialsException e) {
        request.setAttribute("tip", "Resource you requested does not exist");
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }
}
