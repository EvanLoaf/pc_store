package com.gmail.evanloafakahaitao.impl;

import com.gmail.evanloafakahaitao.XmlDao;
import com.gmail.evanloafakahaitao.XmlService;
import com.gmail.evanloafakahaitao.model.ItemXml;
import com.gmail.evanloafakahaitao.util.XmlParser;
import com.gmail.evanloafakahaitao.util.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlServiceImpl implements XmlService {

    private static final Logger logger = LogManager.getLogger(XmlServiceImpl.class);

    private XmlDao xmlDao = new XmlDaoImpl();
    private XmlValidator xmlValidator = new XmlValidator();
    private XmlParser xmlParserService = new XmlParser();

    @Override
    public List<ItemXml> getXmlItems(String xmlFilePath, String schemaFilePath) {
        List<ItemXml> xmlItemsList = new ArrayList<>();
        boolean validation = xmlValidator.validate(xmlFilePath, schemaFilePath);
        logger.info("Item XML is Valid: " + validation);
        if (validation) {
            File xmlFile = xmlDao.getXmlFile(xmlFilePath);
            logger.info("Parsing XML ...");
            xmlItemsList = xmlParserService.parse(xmlFile);
        }
        return xmlItemsList;
    }
}
