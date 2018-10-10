package com.gmail.evanloafakahaitao.pcstore.old.web.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandEnum {
    LOGIN("/dispatcher?command=login"),
    USERS("/dispatcher?command=users"),
    ORDERS("/dispatcher?command=orders"),
    ADD_ORDER("/dispatcher?command=add_order"),
    CONFIRM_ORDER("/dispatcher?command=confirm_order"),
    DELETE_ORDER("/dispatcher?command=delete_order"),
    ITEMS("/dispatcher?command=items"),
    LOAD_ITEMS("/dispatcher?command=load_items"),
    SEND_FEEDBACK("/dispatcher?command=send_feedback"),
    SUBMIT_FEEDBACK("/dispatcher?command=submit_feedback"),
    SHOW_FEEDBACK("/dispatcher?command=show_feedback");

    private static final Logger logger = LogManager.getLogger(RequestEnum.class);

    private final String url;

    CommandEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public static CommandEnum getCommand(String command) {
        try {
            return CommandEnum.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Command " + command.toUpperCase() + " not found", e);
        }
        return null;
    }
}
