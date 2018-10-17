package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.service.XMLService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.properties.XMLProperties;
import com.gmail.evanloafakahaitao.pcstore.service.xml.dto.ItemXMLDTO;
import com.gmail.evanloafakahaitao.pcstore.service.xml.util.FileConverter;
import com.gmail.evanloafakahaitao.pcstore.service.xml.util.XMLItemConverter;
import com.gmail.evanloafakahaitao.pcstore.service.xml.util.XMLParser;
import com.gmail.evanloafakahaitao.pcstore.service.xml.util.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class XMLServiceImpl implements XMLService {

    private static final Logger logger = LogManager.getLogger(XMLServiceImpl.class);

    private final XMLValidator xmlValidator;
    private final XMLParser xmlParser;
    private final FileConverter fileConverter;
    private final XMLItemConverter xmlItemConverter;
    private final XMLProperties xmlProperties;

    @Autowired
    public XMLServiceImpl(
            XMLValidator xmlValidator,
            XMLParser xmlParser,
            FileConverter fileConverter,
            XMLItemConverter xmlItemConverter,
            XMLProperties xmlProperties
    ) {
        this.xmlValidator = xmlValidator;
        this.xmlParser = xmlParser;
        this.fileConverter = fileConverter;
        this.xmlItemConverter = xmlItemConverter;
        this.xmlProperties = xmlProperties;
    }

    @Override
    public List<ItemDTO> getUploadedXmlItems(MultipartFile multipartFile) {
        logger.info("Retrieving XML Items from uploaded File");
        try {
            File itemsFile = fileConverter.convert(multipartFile);
            boolean validation = xmlValidator.validate(itemsFile, xmlProperties.getSchemaFilePath());
            logger.info("Item XML is Valid: " + validation);
            if (validation) {
                logger.info("Parsing XML ...");
                List<ItemXMLDTO> xmlItemsList = xmlParser.parse(itemsFile);
                return xmlItemConverter.toItems(xmlItemsList);
            }
        } catch (IOException e) {
            logger.error("Error converting MultipartFile to File", e);
        }
        return Collections.emptyList();
    }
}
