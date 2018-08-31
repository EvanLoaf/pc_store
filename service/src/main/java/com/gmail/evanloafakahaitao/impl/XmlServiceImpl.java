package com.gmail.evanloafakahaitao.impl;

import com.gmail.evanloafakahaitao.XmlDao;
import com.gmail.evanloafakahaitao.XmlService;
import com.gmail.evanloafakahaitao.model.ItemXml;
import com.gmail.evanloafakahaitao.util.XmlParser;
import com.gmail.evanloafakahaitao.util.XmlValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlServiceImpl implements XmlService {

    private XmlDao xmlDao = new XmlDaoImpl();
    private XmlValidator xmlValidator = new XmlValidator();
    private XmlParser xmlParserService = new XmlParser();

    @Override
    public List<ItemXml> getXmlItems(String xmlFilePath, String schemaFilePath) {
        List<ItemXml> xmlItemsList = new ArrayList<>();
        boolean validation = xmlValidator.validate(xmlFilePath, schemaFilePath);
        System.out.println("Item XML is Valid: " + validation);
        if (validation) {
            File xmlFile = xmlDao.getXmlFile(xmlFilePath);
            System.out.println("Parsing XML ...");
            xmlItemsList = xmlParserService.parse(xmlFile);
        }
        return xmlItemsList;
    }
}
