package com.neu.dreambuilder.controller.common;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.service.common.FileService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/reply/file")
@Api(tags = "答题文件相关接口")
@Slf4j
public class ReplyFileController {

    private static final String[] typeList = new String[]{
            ".mp4", ".mov", ".wmv", ".flv", ".avi", ".avchd", "mkv",
            ".jpg", ".png"
    };

    private static final String FILE_DIR = "reply/";

    private final FileService filService;

    @Autowired
    public ReplyFileController(FileService filService) {
        this.filService = filService;
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('KID')")
    public Result<String> upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf("."));
        for (String type : typeList)
            if (Objects.equals(fileType, type)) {
                String encode = filService.save(file, FILE_DIR);
                if (encode == null) return Result.error("上传失败");
                else return Result.success(encode);
            }
        return Result.error("只能上传媒体文件");
    }


    @GetMapping(value = "/{filename}")
//    @PreAuthorize("hasAuthority('LOGIN')")
    public ResponseEntity<byte[]> download(@PathVariable String filename) {
        try (FileInputStream is = filService.get(filename)) {
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
