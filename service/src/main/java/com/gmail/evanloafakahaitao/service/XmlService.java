package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.dao.model.ItemXml;

import java.util.List;

public interface XmlService {

    List<ItemXml> getXmlItems(String xmlFilePath, String schemaFilePath);
}
