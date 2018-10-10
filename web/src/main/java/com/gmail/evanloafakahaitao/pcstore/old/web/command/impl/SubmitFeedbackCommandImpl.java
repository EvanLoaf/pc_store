package com.gmail.evanloafakahaitao.pcstore.old.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.service.FeedbackService;
import com.gmail.evanloafakahaitao.pcstore.old.web.command.Command;
import com.gmail.evanloafakahaitao.pcstore.old.web.model.UserPrincipal;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.old.web.util.FeedbackValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SubmitFeedbackCommandImpl implements Command {

    @Autowired
    private FeedbackService feedbackService;
    private FeedbackValidator feedbackValidator = new FeedbackValidator();
    @Autowired
    private PageProperties pageProperties;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String feedback = request.getParameter("feedback");
        boolean feedbackValidation = feedbackValidator.validate(feedback);
        if (!feedbackValidation) {
            request.setAttribute("error", "Enter some message up to 200 characters");
            request.setAttribute("feedback", feedback);
            return pageProperties.getFeedbackCreatePagePath();
        }
        HttpSession session = request.getSession();
        UserPrincipal userPrincipal = (UserPrincipal) session.getAttribute("user");
        Long userId = userPrincipal.getId();
        /*int feedbackSaved = feedbackService.save(userId, feedback.trim());
        if (feedbackSaved != 0) {
            response.sendRedirect(request.getContextPath() + CommandEnum.ITEMS.getUrl());
        } else {
            request.setAttribute("error", "Error saving your feedback");
            request.setAttribute("feedback", feedback);
            return configurationManager.getProperty(PageProperties.SEND_FEEDBACK_PAGE_PATH);
        }*/
        return null;
    }
}
