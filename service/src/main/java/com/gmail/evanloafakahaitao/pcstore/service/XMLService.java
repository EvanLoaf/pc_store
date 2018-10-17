package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface XMLService {

    List<ItemDTO> getUploadedXmlItems(MultipartFile multipartFile);
}
