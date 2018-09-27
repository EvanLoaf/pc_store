package com.gmail.evanloafakahaitao.pcstore.service.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class XMLProperties {

    private final Environment environment;

    private String XMLFilePath;
    private String schemaFilePath;

    @Autowired
    public XMLProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initialize() {
        this.XMLFilePath = environment.getProperty("xml.file.path");
        this.schemaFilePath = environment.getProperty("schema.file.path");
    }

    public String getXMLFilePath() {
        return XMLFilePath;
    }

    public String getSchemaFilePath() {
        return schemaFilePath;
    }
}
