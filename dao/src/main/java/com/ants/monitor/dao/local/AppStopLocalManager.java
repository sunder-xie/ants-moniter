package com.ants.monitor.dao.local;

import com.ants.monitor.bean.bizBean.ApplicationChangeBO;

import java.util.List;

/**
 * Created by zxg on 16/1/8.
 * 18:05
 * 内存中的记录 服务停止
 */
public interface AppStopLocalManager {

    //保存停止服务（删除）的当前记录于当前内存中
    void saveStopApp(ApplicationChangeBO applicationChangeBO);

    //移除已经启动的服务于停止列表
    void removeStopApp(ApplicationChangeBO applicationChangeBO);

}
