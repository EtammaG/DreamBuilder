package com.neu.dreambuilder.service.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface FileService {

    String save(MultipartFile file, String fileDir);

    FileInputStream get(String filename) throws FileNotFoundException;
}
