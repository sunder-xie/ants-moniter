package com.ants.monitor.biz.support.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ants.monitor.common.tools.JsonUtil;
import com.ants.monitor.common.tools.SpringContextsUtil;
import com.tqmall.core.common.entity.Result;
import com.tqmall.tqmallstall.domain.param.sms.SmsParam;
import com.tqmall.tqmallstall.service.sms.AppSmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by zxg on 16/1/8.
 * 14:55
 */
@Slf4j
@Component
public class SmsServiceHelp {

    @Autowired
    private AppSmsService appSmsService;

    public Result sendMsg(String mobiles, String action, Map<String, Object> dataMap) {
        if(StringUtils.isEmpty(mobiles)){
            return Result.wrapErrorResult("", "手机号码不能为空");
        }
        if(StringUtils.isEmpty(action)){
            return Result.wrapErrorResult("", "短信模板key，不能为空");
        }

        SmsParam smsParam = new SmsParam();
        smsParam.setSource("ants-dubbo-monitor");
        smsParam.setAction(action);
        smsParam.setMobile(mobiles);

        if(!CollectionUtils.isEmpty(dataMap)){
            try {
                smsParam.setData(JsonUtil.objectToJsonStr(dataMap));
            } catch (Exception e) {
                log.error("", e);
                return Result.wrapErrorResult("", "短信内容可变量替换map转json异常");
            }
        }

        if (null == appSmsService) {
            appSmsService = (AppSmsService) SpringContextsUtil.getBean("appSmsService");
        }
        return appSmsService.sendSms(smsParam);
    }


}
