package com.gmail.evanloafakahaitao.pcstore.temp.web;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ExceptionServlet.class);
    @Autowired
    private PageProperties pageProperties;

    @Override
    public void init() throws ServletException {
        logger.debug("Exception Servlet Init");
    }

    @Override
    public void destroy() {
        logger.debug("Exception Servlet Destroyed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer statusCode = (Integer) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String servletName = (String) req.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        if (servletName == null) {
            servletName = "unknown";
        }
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "unknown";
        }
        logger.info("Error details:");
        logger.info("Status code : " + statusCode);
        logger.info("Servlet name : " + servletName);
        logger.info("Exception type : " + throwable.getClass().getName());
        logger.info("Request Uri : " + requestUri);
        throwable.printStackTrace();

        req.setAttribute("error", statusCode);
        req.setAttribute("error_desc", "Temporary unavailability, please be patient");
        //ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        String page = pageProperties.getErrorsPagePath();
        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }
}
