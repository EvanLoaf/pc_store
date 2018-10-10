package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.xml.dto.ItemXMLDTO;

import java.io.File;
import java.util.List;

public interface XMLService {

    List<ItemXMLDTO> getXmlItems(File xmlFile, String schemaFilePath);
}
