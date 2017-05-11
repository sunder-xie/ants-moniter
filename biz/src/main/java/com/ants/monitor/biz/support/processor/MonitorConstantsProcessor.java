package com.ants.monitor.biz.support.processor;

import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.common.tools.JsonUtil;
import com.ants.monitor.common.tools.http.HttpClientResult;
import com.ants.monitor.common.tools.http.HttpClientUtil;
import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by zxg on 16/3/24.
 * 17:46
 * no bug,以后改代码的哥们，祝你好运~！！
 */
@Service
public class MonitorConstantsProcessor {
    @Value(value = "${cmdb.url}")
    private String cmdbUrl;

    public void setValueToContants() throws IOException {
        String testUrl = cmdbUrl+"/itManage/rest/eth?type=test";
        String devUrl = cmdbUrl+"/itManage/rest/eth?type=DEVELOP";
        String stableUrl = cmdbUrl+"/itManage/rest/eth?type=stable";
        String onlineUrl = cmdbUrl+"/itManage/rest/eth?type=PRODUCTION";

        HttpClientResult testResult = HttpClientUtil.get(testUrl);
        HttpClientResult devResult = HttpClientUtil.get(devUrl);
        HttpClientResult stableResult = HttpClientUtil.get(stableUrl);
        HttpClientResult onlineResult = HttpClientUtil.get(onlineUrl);

        String testJson = testResult.getData();
        JsonNode testJsonNode = JsonUtil.getJsonNode(testJson).get("data");
        initTest(testJsonNode);

        JsonNode devJsonNode = JsonUtil.getJsonNode(devResult.getData()).get("data");
        initTest(devJsonNode);

        JsonNode stableJsonNode = JsonUtil.getJsonNode(stableResult.getData()).get("data");
        initStableOrOnline(stableJsonNode);

        JsonNode onlineJsonNode = JsonUtil.getJsonNode(onlineResult.getData()).get("data");
        initStableOrOnline(onlineJsonNode);

    }



    private void initTest(JsonNode testJsonNode){
        for(int i=0;i<testJsonNode.size();i++){
            JsonNode firstNode = testJsonNode.get(i);
            String hostname = firstNode.get("hostname").toString().replace("\"", "").trim();
            String inHost = firstNode.get("eth0").toString().replace("\"", "").trim();
            String outHost = firstNode.get("eth1").toString().replace("\"", "").trim();

            //测试环境
            MonitorConstants.ecsTestMap.put(inHost,outHost);
            //所有服务器
            MonitorConstants.ecsMap.put(inHost,hostname);
            MonitorConstants.ecsMap.put(outHost,hostname);
            MonitorConstants.ecsBiMap.put(inHost,outHost);
        }
    }


    private void initStableOrOnline(JsonNode jsonNode){
        for(int i=0;i<jsonNode.size();i++){
            JsonNode firstNode = jsonNode.get(i);
            String hostname = firstNode.get("hostname").toString().replace("\"", "").trim();
            String inHost = firstNode.get("eth0").toString().replace("\"", "").trim();
            String outHost = firstNode.get("eth1").toString().replace("\"", "").trim();
            //所有服务器
            MonitorConstants.ecsMap.put(inHost,hostname);
            MonitorConstants.ecsMap.put(outHost,hostname);
            MonitorConstants.ecsBiMap.put(inHost,outHost);
        }
    }
}
