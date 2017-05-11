package com.ants.monitor.bean;

import com.ants.monitor.bean.bizBean.ApplicationChangeBO;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxg on 15/11/16.
 * 常量
 */
public class MonitorConstants {
    public static final String OWNER = "owner";
    public static final String ORGANICATION = "organization";
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";


    public static final String SESSION_USER_NAME = "SESSION_USER_NAME";

    // sms发短信相关变量
    public static final String SMS_STOP_ACTION = "monitor_app_stop";
    //加密header头
    public static final String HEADER = "Tqmall.monitor.Header./*";

    //内存中存储停止服务的应用{appName+ host+ip:time+通知次数}
    public static final Map<ApplicationChangeBO,String> appStopMap = new HashMap<>();




    /**=============ip 所在的服务器===================================**/

    //存ip：name
    public static final Map<String,String> ecsMap = new HashMap<>();
    // 双向map
    public static final BiMap<String,String> ecsBiMap = HashBiMap.create();

    //测试环境的ip
    public static final Map<String,String> ecsTestMap = new HashMap<>();

    public static void initEcsMap(){
//        //测试环境的内网外网ip对应
//        ecsTestMap.put("10.160.66.192","112.124.66.38");
//        ecsTestMap.put("10.162.100.213","121.41.128.78");
//        ecsTestMap.put("10.162.66.236","115.29.245.103");
//        ecsTestMap.put("10.161.159.73","115.29.220.170");
//        ecsTestMap.put("10.165.6.179","114.215.178.133");
//
//        //ip对应的名称
//        ecsMap.put("115.29.220.178","webserver");
//        ecsMap.put("115.29.195.156","webserver2");
//        ecsMap.put("115.29.11.2","appserver");
//        ecsMap.put("115.29.205.51","Search_engine_goods");
//        ecsMap.put("115.29.11.211","appserver2");
//        ecsMap.put("121.41.58.245","appserver13");
//        ecsMap.put("114.215.237.83","appserver4");
//        ecsMap.put("121.40.202.220","appserver8");
//        ecsMap.put("121.199.57.39","appserver19");
//        ecsMap.put("112.124.120.128","appserver7");
//        ecsMap.put("121.40.196.235","appserver14");
//        ecsMap.put("115.29.209.131","legend_online1");
//        ecsMap.put("114.215.185.228","legend_online2");
//        ecsMap.put("114.215.186.86","appserver16");
//        ecsMap.put("115.29.171.82","tqmalldb2");
//        ecsMap.put("121.199.23.203","tqmalldb3");
//        ecsMap.put("121.199.31.235","winserver1");
//        ecsMap.put("114.215.185.110","winserver2");
//        ecsMap.put("121.199.43.134","gitlab");
//        ecsMap.put("114.215.237.91","tqmalldw");
//        ecsMap.put("114.215.169.34","tqmallbuild");
//        ecsMap.put("121.199.42.91","tqmallstable");
//        ecsMap.put("114.215.169.216","appserver3");
//        ecsMap.put("121.40.175.192","appserver6");
//        ecsMap.put("114.215.178.14","legend_stable");
//        ecsMap.put("121.40.123.39","jumpserver");
//        ecsMap.put("112.124.66.38","tqmalltest3");
//        ecsMap.put("115.29.245.103","tqmalltest5");
//        ecsMap.put("121.40.175.187","Search_engine");
//        ecsMap.put("115.29.220.170","tqmalltest");
//        ecsMap.put("114.215.178.133","tqmalltest2");
//        ecsMap.put("112.124.11.106","Search_data");
//        ecsMap.put("121.40.248.46","video_monitor");
//        ecsMap.put("112.124.22.251","Jenkins_StressTest");
//        ecsMap.put("121.199.28.244","MySQL_Server");
//        ecsMap.put("121.41.128.78","Test_Marketing");
//        ecsMap.put("10.161.129.146","webserver");
//        ecsMap.put("10.161.182.198","webserver2");
//        ecsMap.put("10.122.66.105","appserver");
//        ecsMap.put("10.161.236.98","Search_engine_goods");
//        ecsMap.put("10.122.66.103","appserver2");
//        ecsMap.put("10.168.198.19","appserver13");
//        ecsMap.put("10.162.51.140","appserver4");
//        ecsMap.put("10.168.86.137","appserver8");
//        ecsMap.put("10.132.56.252","appserver19");
//        ecsMap.put("10.162.67.71","appserver7");
//        ecsMap.put("10.168.64.242","appserver14");
//        ecsMap.put("10.161.216.71","legend_online1");
//        ecsMap.put("10.162.86.162","legend_online2");
//        ecsMap.put("10.165.6.247","appserver16");
//        ecsMap.put("10.161.182.197","tqmalldb2");
//        ecsMap.put("10.132.27.103","tqmalldb3");
//        ecsMap.put("10.132.41.234","winserver1");
//        ecsMap.put("10.162.98.58","winserver2");
//        ecsMap.put("10.132.51.105","gitlab");
//        ecsMap.put("10.162.51.135","tqmalldw");
//        ecsMap.put("10.162.101.44","tqmallbuild");
//        ecsMap.put("10.132.51.216","tqmallstable");
//        ecsMap.put("10.162.101.46","appserver3");
//        ecsMap.put("10.168.52.134","appserver6");
//        ecsMap.put("10.162.97.209","legend_stable");
//        ecsMap.put("10.168.22.192","jumpserver");
//        ecsMap.put("10.160.66.192","tqmalltest3");
//        ecsMap.put("10.162.66.236","tqmalltest5");
//        ecsMap.put("10.168.103.130","Search_engine");
//        ecsMap.put("10.161.159.73","tqmalltest");
//        ecsMap.put("10.165.6.179","tqmalltest2");
//        ecsMap.put("10.160.16.177","Search_data");
//        ecsMap.put("10.168.138.150","video_monitor");
//        ecsMap.put("10.160.1.205","Jenkins_StressTest");
//        ecsMap.put("10.132.84.179","MySQL_Server");
//        ecsMap.put("10.162.100.213","Test_Marketing");
//
//        ecsMap.put("120.26.90.77","grace1");
//        ecsMap.put("10.117.209.99","grace1");
//        ecsMap.put("120.26.223.21","grace2");
//        ecsMap.put("10.117.51.149","grace2");
//        ecsMap.put("10.161.162.11","Dandelion");
//        ecsMap.put("115.29.177.1","Dandelion");
//
//
//
//        ecsBiMap.put("10.161.162.11","115.29.177.1");
//        ecsBiMap.put("10.117.209.99","120.26.90.77");
//        ecsBiMap.put("10.117.51.149","120.26.223.21");
//        ecsBiMap.put("10.161.129.146","115.29.220.178");
//        ecsBiMap.put("10.161.182.198","115.29.195.156");
//        ecsBiMap.put("10.122.66.105","115.29.11.2");
//        ecsBiMap.put("10.161.236.98","115.29.205.51");
//        ecsBiMap.put("10.122.66.103","115.29.11.211");
//        ecsBiMap.put("10.168.198.19","121.41.58.245");
//        ecsBiMap.put("10.162.51.140","114.215.237.83");
//        ecsBiMap.put("10.168.86.137","121.40.202.220");
//        ecsBiMap.put("10.132.56.252","121.199.57.39");
//        ecsBiMap.put("10.162.67.71","112.124.120.128");
//        ecsBiMap.put("10.168.64.242","121.40.196.235");
//        ecsBiMap.put("10.161.216.71","115.29.209.131");
//        ecsBiMap.put("10.162.86.162","114.215.185.228");
//        ecsBiMap.put("10.165.6.247","114.215.186.86");
//        ecsBiMap.put("10.161.182.197","115.29.171.82");
//        ecsBiMap.put("10.132.27.103","121.199.23.203");
//        ecsBiMap.put("10.132.41.234","121.199.31.235");
//        ecsBiMap.put("10.162.98.58","114.215.185.110");
//        ecsBiMap.put("10.132.51.105","121.199.43.134");
//        ecsBiMap.put("10.162.51.135","114.215.237.91");
//        ecsBiMap.put("10.162.101.44","114.215.169.34");
//        ecsBiMap.put("10.132.51.216","121.199.42.91");
//        ecsBiMap.put("10.162.101.46","114.215.169.216");
//        ecsBiMap.put("10.168.52.134","121.40.175.192");
//        ecsBiMap.put("10.162.97.209","114.215.178.14");
//        ecsBiMap.put("10.168.22.192","121.40.123.39");
//        ecsBiMap.put("10.160.66.192","112.124.66.38");
//        ecsBiMap.put("10.162.66.236","115.29.245.103");
//        ecsBiMap.put("10.168.103.130","121.40.175.187");
//        ecsBiMap.put("10.161.159.73","115.29.220.170");
//        ecsBiMap.put("10.165.6.179","114.215.178.133");
//        ecsBiMap.put("10.160.16.177","112.124.11.106");
//        ecsBiMap.put("10.168.138.150","121.40.248.46");
//        ecsBiMap.put("10.160.1.205","112.124.22.251");
//        ecsBiMap.put("10.132.84.179","121.199.28.244");
//        ecsBiMap.put("10.162.100.213","121.41.128.78");
    }


}
