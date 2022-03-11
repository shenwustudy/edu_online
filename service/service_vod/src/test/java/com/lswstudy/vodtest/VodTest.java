package com.lswstudy.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @author lswstudy
 * @create 2022-02-18-11:37
 */
public class VodTest {
    public static void main(String[] args) throws Exception{
        String accessKeyId = "LTAI5tJqTr3xK8a8t87EZbve";
        String accessKeySecret = "aCuzqKiaTuDafUfFXjmQEu68m6kJQW";
        //上传到阿里云的视频文件名称
        String title = "sky";
        //本地视频文件全路径
        String fileName = "D:/.视频素材库/sky.mp4";

        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    public static void getPlayAuth() throws Exception{
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tJqTr3xK8a8t87EZbve", "aCuzqKiaTuDafUfFXjmQEu68m6kJQW");

        //获取视频地址的request和response对象
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        //向request中设置视频的id
        request.setVideoId("d987c1b0c8214b91b2c59f51215f18b0");

        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);

        //获取播放凭证
        String playAuth = response.getPlayAuth();
        System.out.println(playAuth);
    }

    public static void getPlayUrl() throws Exception{
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tJqTr3xK8a8t87EZbve", "aCuzqKiaTuDafUfFXjmQEu68m6kJQW");

        //获取视频地址的request和response对象
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向request中设置视频的id
        request.setVideoId("d987c1b0c8214b91b2c59f51215f18b0");

        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //获取播放地址
        for(GetPlayInfoResponse.PlayInfo playInfo : playInfoList){
            System.out.println("PlayInfo.PlayUrl = " + playInfo.getPlayURL());
        }
        //获取Base信息
        System.out.println("VideoBase.Title = " + response.getVideoBase().getTitle());
    }
}
