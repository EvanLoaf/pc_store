package com.gmail.evanloafakahaitao.pcstore.service.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class XMLProperties {

    @Value("${schema.file.path}")
    private String schemaFilePath;

    public String getSchemaFilePath() {
        return schemaFilePath;
    }
}
