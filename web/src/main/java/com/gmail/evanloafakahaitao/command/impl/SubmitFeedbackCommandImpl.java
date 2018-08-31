package com.gmail.evanloafakahaitao.command.impl;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.FeedbackService;
import com.gmail.evanloafakahaitao.impl.FeedbackServiceImpl;
import com.gmail.evanloafakahaitao.command.Command;
import com.gmail.evanloafakahaitao.model.CommandEnum;
import com.gmail.evanloafakahaitao.model.UserPrincipal;
import com.gmail.evanloafakahaitao.util.FeedbackValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubmitFeedbackCommandImpl implements Command {

    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private FeedbackService feedbackService = new FeedbackServiceImpl();
    private FeedbackValidator feedbackValidator = new FeedbackValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String feedback = request.getParameter("feedback");
        boolean feedbackValidation = feedbackValidator.validate(feedback);
        if (!feedbackValidation) {
            request.setAttribute("error", "Enter some message up to 200 characters");
            request.setAttribute("feedback", feedback);
            return configurationManager.getProperty(PageProperties.SEND_FEEDBACK_PAGE_PATH);
        }
        HttpSession session = request.getSession();
        UserPrincipal userPrincipal = (UserPrincipal) session.getAttribute("user");
        Long userId = userPrincipal.getId();
        int feedbackSaved = feedbackService.save(userId, feedback.trim());
        if (feedbackSaved != 0) {
            response.sendRedirect(request.getContextPath() + CommandEnum.ITEMS.getUrl());
        } else {
            request.setAttribute("error", "Error saving your feedback");
            request.setAttribute("feedback", feedback);
            return configurationManager.getProperty(PageProperties.SEND_FEEDBACK_PAGE_PATH);
        }
        return null;
    }
}
