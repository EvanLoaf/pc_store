package com.gmail.evanloafakahaitao.pcstore.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.pcstore.service.FeedbackService;
import com.gmail.evanloafakahaitao.pcstore.service.impl.FeedbackServiceImpl;
import com.gmail.evanloafakahaitao.pcstore.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowFeedbackCommandImpl implements Command {

    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private FeedbackService feedbackService = new FeedbackServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*List<Feedback> feedbackList = feedbackService.findAll();
        if (feedbackList == null) {
            request.setAttribute("error", "Error retrieving feedback");
            return configurationManager.getProperty(PageProperties.USERS_PAGE_PATH);
        } else {
            request.setAttribute("feedback", feedbackList);
            return configurationManager.getProperty(PageProperties.SHOW_FEEDBACK_PAGE_PATH);
        }*/


        // DELETE THIS RETURN, TEMP INSERTED
        return null;
    }
}
