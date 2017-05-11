package com.ants.monitor.controller.rest;

import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.common.Sha1Util;
import com.ants.monitor.dao.redisManager.ApplicationBaseRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zxg on 16/1/8.
 * 19:55
 * 应用和负责人电话的app
 */
@RestController
@RequestMapping("/monitor/rest/appPhone")
@Slf4j
public class ApplicationPhoneRestController {

    @Autowired
    private ApplicationBaseRedisManager applicationBaseRedisManager;


    @RequestMapping(value = "/getPhone", method = RequestMethod.GET)
    public Result getPhone(String appName){
        return Result.wrapSuccessfulResult(applicationBaseRedisManager.getPhoneByAppName(appName));
    }

    @RequestMapping(value = "/getAllPhone", method = RequestMethod.GET)
    public Result getAllPhone(){
        return Result.wrapSuccessfulResult(applicationBaseRedisManager.getAllAppPhone());
    }

    @RequestMapping(value = "/saveAppPhone")
    public Result getAllPhone(String appName,String phone,long time, String compareKey){
        try {
            long now = System.currentTimeMillis();

            Long diff = now - time;
            //规定有效期为2个小时
            Long hour = diff / (1000 * 60 * 60);
            if (hour > 2) {
                return Result.wrapErrorResult("001", "超时,相隔时间过长");
            }
            //生成密钥比较
            String oneKey = Sha1Util.getSha1(MonitorConstants.HEADER + time);
            if (!compareKey.equals(oneKey)) {
                return Result.wrapErrorResult("002", "密钥错误");
            }

            //保存数据
            applicationBaseRedisManager.saveApplicationPhone(appName,phone);

            return Result.wrapSuccessfulResult("success");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.wrapErrorResult("003",e.getMessage());
        }
    }


}
