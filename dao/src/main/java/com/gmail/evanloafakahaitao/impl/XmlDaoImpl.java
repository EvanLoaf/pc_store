package com.gmail.evanloafakahaitao.impl;

import com.gmail.evanloafakahaitao.XmlDao;

import java.io.File;

public class XmlDaoImpl implements XmlDao {
    @Override
    public File getXmlFile(String xmlFilePath) {
        return new File(xmlFilePath);
    }
}
