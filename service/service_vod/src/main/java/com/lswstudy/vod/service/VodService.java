package com.lswstudy.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lswstudy
 * @create 2022-02-20-16:29
 */
public interface VodService {
    String uploadVideo(MultipartFile file);

    void removeVideoById(String videoId);

    void removeVideoListById(List list);
}
