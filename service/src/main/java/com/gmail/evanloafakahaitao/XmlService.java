package com.gmail.evanloafakahaitao;

import com.gmail.evanloafakahaitao.model.ItemXml;

import java.util.List;

public interface XmlService {

    List<ItemXml> getXmlItems(String xmlFilePath, String schemaFilePath);
}
