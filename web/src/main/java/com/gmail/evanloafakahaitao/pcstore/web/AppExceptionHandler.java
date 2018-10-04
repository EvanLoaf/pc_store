package com.gmail.evanloafakahaitao.pcstore.web;

import com.gmail.evanloafakahaitao.pcstore.web.properties.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }
}
