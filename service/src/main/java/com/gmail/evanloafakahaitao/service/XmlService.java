package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.service.dto.ItemXMLDTO;

import java.util.List;

public interface XMLService {

    List<ItemXMLDTO> getXmlItems(String xmlFilePath, String schemaFilePath);
}
