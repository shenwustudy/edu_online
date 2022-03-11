package com.lswstudy.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lswstudy.oss.service.OssService;
import com.lswstudy.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author lswstudy
 * @create 2021-12-17-14:30
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        OSS ossClient = null;
        try {
            //创建OssClient实例
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            //获取文件名称，给文件名称添加一个随机值(uuid),同时按照时间分类,避免出现上传相同名称的文件时最后一次上传的文件覆盖之前上传的文件的现象
            String fileName = new DateTime().toString("yyyy/MM/dd") + "/" + UUID.randomUUID().toString().replaceAll("-","") + file.getOriginalFilename();
            ossClient.putObject(bucketName, fileName, inputStream);
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null){
                ossClient.shutdown();
            }
        }
    }
}
