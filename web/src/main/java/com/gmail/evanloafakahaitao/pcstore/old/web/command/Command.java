package com.gmail.evanloafakahaitao.pcstore.old.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}