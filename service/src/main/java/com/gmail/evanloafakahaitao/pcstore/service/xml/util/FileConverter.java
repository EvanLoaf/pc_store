package com.gmail.evanloafakahaitao.pcstore.service.xml.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class FileConverter {

    private static final Logger logger = LogManager.getLogger(FileConverter.class);

    public File convert(MultipartFile multipartFile) throws IOException {
        logger.info("Converting MultipartFile to File");
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        multipartFile.transferTo(file);
        return file;
    }
}
