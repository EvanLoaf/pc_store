package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.XMLDao;
import com.gmail.evanloafakahaitao.service.XMLService;
import com.gmail.evanloafakahaitao.dao.impl.XMLDaoImpl;
import com.gmail.evanloafakahaitao.service.dto.ItemXMLDTO;
import com.gmail.evanloafakahaitao.service.util.XMLParser;
import com.gmail.evanloafakahaitao.service.util.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLServiceImpl implements XMLService {

    private static final Logger logger = LogManager.getLogger(XMLServiceImpl.class);

    private XMLDao xmlDao = new XMLDaoImpl();
    private XMLValidator xmlValidator = new XMLValidator();
    private XMLParser xmlParserService = new XMLParser();

    @Override
    public List<ItemXMLDTO> getXmlItems(String xmlFilePath, String schemaFilePath) {
        List<ItemXMLDTO> xmlItemsList = new ArrayList<>();
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
