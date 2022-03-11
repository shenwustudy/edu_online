package com.lswstudy.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lswstudy
 * @create 2021-12-17-14:29
 */
@Service
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
