package com.gmail.evanloafakahaitao.web;

import com.gmail.evanloafakahaitao.web.command.Command;
import com.gmail.evanloafakahaitao.web.command.impl.*;
import com.gmail.evanloafakahaitao.web.model.CommandEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DispatcherServlet.class);

    private Map<CommandEnum, Command> commands = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        Command commandAction = null;
        try {
            commandAction = commands.get(CommandEnum.getCommand(command));
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        }
        if (commandAction != null) {
            try {
                String page = commandAction.execute(req, resp);
                if (page != null) {
                    getServletContext().getRequestDispatcher(page).forward(req, resp);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            logger.info("Command -- %s -- does not exist\n", command);
        }
    }

    @Override
    public void init() throws ServletException {
        commands.put(CommandEnum.LOGIN, new LoginCommandImpl());
        commands.put(CommandEnum.ITEMS, new ItemsCommandImpl());
        commands.put(CommandEnum.ORDERS, new OrdersCommandImpl());
        commands.put(CommandEnum.ADD_ORDER, new AddOrderCommandImpl());
        commands.put(CommandEnum.DELETE_ORDER, new DeleteOrderCommandImpl());
        commands.put(CommandEnum.LOAD_ITEMS, new LoadItemsCommandImpl());
        commands.put(CommandEnum.USERS, new UsersCommandImpl());
        commands.put(CommandEnum.SEND_FEEDBACK, new SendFeedbackCommandImpl());
        commands.put(CommandEnum.SUBMIT_FEEDBACK, new SubmitFeedbackCommandImpl());
        commands.put(CommandEnum.SHOW_FEEDBACK, new ShowFeedbackCommandImpl());
        commands.put(CommandEnum.CONFIRM_ORDER, new ConfirmOrderCommandImpl());
    }
}
