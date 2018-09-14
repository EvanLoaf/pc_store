package com.gmail.evanloafakahaitao.pcstore.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.pcstore.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendFeedbackCommandImpl implements Command {

    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return configurationManager.getProperty(PageProperties.SEND_FEEDBACK_PAGE_PATH);
    }
}
