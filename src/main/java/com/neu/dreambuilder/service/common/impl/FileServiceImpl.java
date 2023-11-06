package com.neu.dreambuilder.service.common.impl;

import com.neu.dreambuilder.common.utils.JwtUtil;
import com.neu.dreambuilder.service.common.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${dream-builder.sys.file-save-path}")
    private String FILE_PATH;

    @Override
    public String save(MultipartFile file, String fileDir) {
        if (file.isEmpty()) return null;

        String filePath = FILE_PATH + fileDir;
        File temp = new File(filePath);
        if (!temp.exists()) temp.mkdirs();

        String originalFilename = file.getOriginalFilename();
        int i = originalFilename.lastIndexOf(".");
        String filename = originalFilename.substring(0, i);
        String filetype = originalFilename.substring(i);

        File localFile = new File(filePath + originalFilename);
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            log.warn("文件报错失败", e);
            return null;
        }
        return JwtUtil.createJWT(fileDir + filename) + filetype;
    }

    @Override
    public FileInputStream get(String filename) throws FileNotFoundException {
        int i = filename.lastIndexOf(".");
        String name = filename.substring(0, i);
        String type = filename.substring(i);
        return new FileInputStream(
                FILE_PATH
                        + JwtUtil.parseJWT(name).getBody().getSubject()
                        + type);
    }
}
