package com.gmail.evanloafakahaitao.pcstore.controller.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class FileConverter {

    public File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        multipartFile.transferTo(file);
        return file;
    }
}
