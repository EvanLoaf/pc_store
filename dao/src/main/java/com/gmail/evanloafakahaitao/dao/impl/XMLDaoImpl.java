package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.XMLDao;

import java.io.File;

public class XMLDaoImpl implements XMLDao {
    @Override
    public File getXmlFile(String xmlFilePath) {
        return new File(xmlFilePath);
    }
}
