package com.lswstudy.edusms.service.impl;


import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.lswstudy.edusms.service.SmsService;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author lswstudy
 * @create 2022-02-24-16:58
 */
@Service
public class SmsServiceImpl implements SmsService {
    // 短信应用SDK AppID
    int appId = 1400636084; // 1400开头
    // 短信应用SDK AppKey
    String appKey = "7141dcf772a8a76adfa1121d22c76d8e";
    // 短信模板ID
    int templateId = 1314568;
    // 签名内容
    String smsSign = "林申武学习分布式项目开发";
    // 验证码
    String str = "000000";

    /**
     * 发送短信验证码
     * @param phoneNum 需要发送给哪个手机号码
     * @return 验证码，若为000000，则发送失败
     */
    @Override
    public String sendMessage(String phoneNum) {
        try {
            // 随即6位数赋值验证码
            String codeStr = (int) ((Math.random() * 9 + 1) * 100000) + "";

            /*
            这里的参数和腾讯云短信模板中设置的参数保持一致
            第一个参数是验证码，第二个参数是有效时间
             */
            String[] params = {codeStr , "5"};
            SmsSingleSender ssender = new SmsSingleSender(appId, appKey);

            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNum, templateId, params, smsSign, "", "");

            // 发送成功则给验证码赋值
            if (result.result == 0) {
                str = codeStr;
            }
        } catch (HTTPException e1) {
            // HTTP响应码错误
            e1.printStackTrace();
        } catch (JSONException e2) {
            // json解析错误
            e2.printStackTrace();
        } catch (IOException e3) {
            // 网络IO错误
            e3.printStackTrace();
        }
        return str;
    }

}
