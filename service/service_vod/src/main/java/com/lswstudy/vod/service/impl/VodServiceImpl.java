package com.lswstudy.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.lswstudy.servicebase.exceptionhandler.EduException;
import com.lswstudy.vod.service.VodService;
import com.lswstudy.vod.utils.ConstantVodUtils;
import com.lswstudy.vod.utils.InitVodClientUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.List;

/**
 * @author lswstudy
 * @create 2022-02-20-16:29
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        UploadStreamResponse response = null;
        InputStream inputStream = null;
        try {
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0,fileName.indexOf("."));
            inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            response = uploader.uploadStream(request);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return response.getVideoId();
    }

    @Override
    public void removeVideoById(String videoId) {
        try{
            DefaultAcsClient client = InitVodClientUtils.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            client.getAcsResponse(request);
        }catch (Exception e){
            throw new EduException(20001, "视频删除失败");
        }
    }

    @Override
    public void removeVideoListById(List list) {
        try{
            DefaultAcsClient client = InitVodClientUtils.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            String ids = StringUtils.join(list.toArray(), ",");
            request.setVideoIds(ids);
            client.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
