package com.gmail.evanloafakahaitao.pcstore.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Logging Filter Init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        logger.info(
                "#LOG_INFO " + new Date() + req.getHeader("User-Agent") + " URL = " + req.getRequestURL() + (req.getQueryString() == null ? "" : "?" + req.getQueryString())
        );
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.debug("Logging Filter Destroyed");
    }
}
