package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.xml.dto.ItemXMLDTO;

import java.util.List;

public interface XMLService {

    List<ItemXMLDTO> getXmlItems(String xmlFilePath, String schemaFilePath);
}
