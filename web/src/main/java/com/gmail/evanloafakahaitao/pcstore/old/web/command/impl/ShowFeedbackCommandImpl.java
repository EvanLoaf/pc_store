package com.gmail.evanloafakahaitao.pcstore.old.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.service.FeedbackService;
import com.gmail.evanloafakahaitao.pcstore.old.web.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ShowFeedbackCommandImpl implements Command {

    @Autowired
    private FeedbackService feedbackService;

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
