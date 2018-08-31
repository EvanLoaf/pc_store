package com.gmail.evanloafakahaitao.model;

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
            System.out.println("Command " + command.toUpperCase() + " not found");
            e.printStackTrace();
        }
        return null;
    }
}
