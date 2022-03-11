package com.lswstudy.oss.controller;

import com.lswstudy.commonutils.ResultData;
import com.lswstudy.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lswstudy
 * @create 2021-12-17-14:29
 */
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像
    @PostMapping
    public ResultData uploadOssFile(MultipartFile file){
        String avatarUrl = ossService.uploadFileAvatar(file);
        return ResultData.ok().data("avatarUrl",avatarUrl);
    }
}
