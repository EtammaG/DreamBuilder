package com.neu.dreambuilder.controller.common;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.service.common.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public abstract class FileController {

    public abstract String[] typeList();

    public abstract String fileDir();

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    public Result<String> upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf("."));
        for (String type : typeList())
            if (Objects.equals(fileType, type)) {
                String encode = fileService.save(file, fileDir());
                if (encode == null) return Result.error("上传失败");
                else return Result.success(encode);
            }
        return Result.error("只能上传媒体文件");
    }

    public ResponseEntity<byte[]> download(String filename) {
        try (FileInputStream is = fileService.get(filename)) {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment;filename=" + filename);
            HttpStatus statusCode = HttpStatus.OK;
            return new ResponseEntity<>(bytes, headers, statusCode);
        } catch (IOException e) {
            log.warn("文件读取失败", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
