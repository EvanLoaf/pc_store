package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.XMLDao;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class XMLDaoImpl implements XMLDao {
    @Override
    public File getXmlFile(String xmlFilePath) {
        return new File(xmlFilePath);
    }
}
