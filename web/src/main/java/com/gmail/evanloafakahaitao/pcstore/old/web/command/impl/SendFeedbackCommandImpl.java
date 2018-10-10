package com.gmail.evanloafakahaitao.pcstore.old.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.old.web.command.Command;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendFeedbackCommandImpl implements Command {

    @Autowired
    private PageProperties pageProperties;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return pageProperties.getFeedbackCreatePagePath();
    }
}
