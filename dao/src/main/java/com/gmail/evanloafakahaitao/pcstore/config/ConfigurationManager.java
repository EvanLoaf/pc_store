package com.gmail.evanloafakahaitao.pcstore.config;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static ConfigurationManager instance;

    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "config";

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
