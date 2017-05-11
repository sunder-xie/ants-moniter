package com.ants.monitor.biz.support.processor;

import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.bean.bizBean.ApplicationChangeBO;
import com.ants.monitor.dao.local.AppStopLocalManager;
import com.ants.monitor.dao.redisManager.AppChangeRedisManager;
import com.ants.monitor.dao.redisManager.AppStopRedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by zxg on 16/1/5.
 * 15:20
 * 应用停止和启动事件捕获后的处理机制
 * 可做邮件通知、电话通知、短信通知等相关应用负责人的代码
 */
@Service
public class NotifyAppChangeProcessor {
    // 是否是线上
    @Value(value = "${is_online}")
    private String is_online;

    @Autowired
    private AppStopRedisManager appStopRedisManager;

    public void stopApp(ApplicationChangeBO applicationChangeBO){
        //todo
        Boolean online = Boolean.parseBoolean(is_online);
        if(online){
            //将断线的app放入redis，等待task定时扫描
            appStopRedisManager.saveStopApp(applicationChangeBO,0);
        }

    }

    public void startApp(ApplicationChangeBO applicationChangeBO){
        //todo
        Boolean online = Boolean.parseBoolean(is_online);
        if(online){
            appStopRedisManager.removeStopApp(applicationChangeBO);
        }
    }
}
