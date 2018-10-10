package com.gmail.evanloafakahaitao.pcstore.service.xml.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class XMLValidator {

    private static final Logger logger = LogManager.getLogger(XMLValidator.class);

    public boolean validate(File xmlFile, String xsdRoute) {
        try (
                InputStream xml = new FileInputStream(xmlFile);
                InputStream xsd = new FileInputStream(xsdRoute)
        ) {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
