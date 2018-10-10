package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.XMLDao;
import com.gmail.evanloafakahaitao.pcstore.service.XMLService;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.XMLDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.service.xml.dto.ItemXMLDTO;
import com.gmail.evanloafakahaitao.pcstore.service.xml.util.XMLParser;
import com.gmail.evanloafakahaitao.pcstore.service.xml.util.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class XMLServiceImpl implements XMLService {

    private static final Logger logger = LogManager.getLogger(XMLServiceImpl.class);

    private final XMLValidator xmlValidator;
    private final XMLParser xmlParser;

    @Autowired
    public XMLServiceImpl(
            XMLValidator xmlValidator,
            XMLParser xmlParser
    ) {
        this.xmlValidator = xmlValidator;
        this.xmlParser = xmlParser;
    }

    @Override
    public List<ItemXMLDTO> getXmlItems(File xmlFile, String schemaFilePath) {
        List<ItemXMLDTO> xmlItemsList = new ArrayList<>();
        boolean validation = xmlValidator.validate(xmlFile, schemaFilePath);
        logger.info("Item XML is Valid: " + validation);
        if (validation) {
            logger.info("Parsing XML ...");
            xmlItemsList = xmlParser.parse(xmlFile);
        }
        return xmlItemsList;
    }
}
