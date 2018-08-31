package com.gmail.evanloafakahaitao.command.impl;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.model.Feedback;
import com.gmail.evanloafakahaitao.FeedbackService;
import com.gmail.evanloafakahaitao.impl.FeedbackServiceImpl;
import com.gmail.evanloafakahaitao.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowFeedbackCommandImpl implements Command {

    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private FeedbackService feedbackService = new FeedbackServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Feedback> feedbackList = feedbackService.findAll();
        if (feedbackList == null) {
            request.setAttribute("error", "Error retrieving feedback");
            return configurationManager.getProperty(PageProperties.USERS_PAGE_PATH);
        } else {
            request.setAttribute("feedback", feedbackList);
            return configurationManager.getProperty(PageProperties.SHOW_FEEDBACK_PAGE_PATH);
        }
    }
}
