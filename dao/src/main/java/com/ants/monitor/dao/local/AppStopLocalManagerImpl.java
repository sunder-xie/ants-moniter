package com.ants.monitor.dao.local;

import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.bean.bizBean.ApplicationChangeBO;
import com.ants.monitor.common.redis.RedisKeyBean;
import com.ants.monitor.common.tools.JsonUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zxg on 16/1/8.
 * 18:05
 */
@Component
public class AppStopLocalManagerImpl implements AppStopLocalManager {

    @Override
    public void saveStopApp(ApplicationChangeBO applicationChangeBO) {

        ApplicationChangeBO filedBO = new ApplicationChangeBO();
        filedBO.setAppName(applicationChangeBO.getAppName());
        filedBO.setHost(applicationChangeBO.getHost());
        filedBO.setPort(applicationChangeBO.getPort());

        String value = applicationChangeBO.getTime()+","+"0";
        MonitorConstants.appStopMap.put(filedBO, value);

    }

    @Override
    public void removeStopApp(ApplicationChangeBO applicationChangeBO) {

        ApplicationChangeBO filedBO = new ApplicationChangeBO();
        filedBO.setAppName(applicationChangeBO.getAppName());
        filedBO.setHost(applicationChangeBO.getHost());
        filedBO.setPort(applicationChangeBO.getPort());
        if(MonitorConstants.appStopMap.containsKey(filedBO)) {
            MonitorConstants.appStopMap.remove(filedBO);
        }
    }

}
