package com.gmail.evanloafakahaitao.pcstore.controller.filter;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @Autowired
    private final PageProperties pageProperties;

    public AppExceptionHandler(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    //TODO access exc
    @ExceptionHandler(AccessDeniedException.class)
    public String accessErrorHandler(HttpServletRequest request, Exception e) {

        return null;
    }

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }
}
