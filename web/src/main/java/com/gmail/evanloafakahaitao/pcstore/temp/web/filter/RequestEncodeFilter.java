package com.gmail.evanloafakahaitao.pcstore.temp.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class RequestEncodeFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(RequestEncodeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Request Encode Filter Init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
        servletResponse.setContentType("text/html; charset=UTF-8");
    }

    @Override
    public void destroy() {
        logger.debug("Request Encode Filter Destroyed");
    }
}
