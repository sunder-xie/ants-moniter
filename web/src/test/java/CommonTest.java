import com.ants.monitor.common.tools.JsonUtil;
import com.ants.monitor.common.tools.TimeUtil;
import com.ants.monitor.common.tools.http.HttpClientResult;
import com.ants.monitor.common.tools.http.HttpClientUtil;
import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * Created by zxg on 16/1/12.
 * 17:36
 */
public class CommonTest {
    @Test
    public void testMap(){
        Map<String,Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add("123");
        map.put("a",set);

        Set<String> new_set = map.get("a");
        new_set.add("23113");


        Set<String> test_set = map.get("a");
        System.out.println(test_set.size());
    }


    @Test
    public void MapTojson(){
        Map<String,Map<String,Integer>> reportMap = new HashMap<>();
        Map<String,Integer> a = new HashMap<>();
        Map<String,Integer> b = new HashMap<>();
        a.put("test",2);
        a.put("test2",23);
        a.put("test3",12);
        reportMap.put("provider", a);
        reportMap.put("consumer",b);

        String jsons = JsonUtil.objectToJsonStr(reportMap);

        Map<String,Map<String,Integer>> hha = JsonUtil.jsonStrToObject(jsons, HashMap.class);
        Integer c = hha.get("provider").get("test3");
        System.out.println("");
    }


    @Test
    public void testJsonToMap(){
        String jsons = "{\n  \"provider\" : {\n    \"tqmallstall\" : 3\n  },\n  \"consumer\" : {\n    \"legend\" : 843,\n    \"tqmallstall\" : 46\n  }\n}";
        Map<String,Map<String,Integer>> hha = JsonUtil.jsonStrToObject(jsons, Map.class);
        System.out.println(hha);
    }
    @Test
    public void testDay(){
        String yesterDay = TimeUtil.getBeforDateByNumber(new Date(), -1);
        System.out.println(yesterDay);
    }


    @Test
    public void testIntern(){
        String str1 = new StringBuilder("计算机").append("haha").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va1").toString();
        System.out.println(str2.intern() == str2);

    }


    @Test
    public void testHttp() throws IOException {
        String cmdbUrl = "http://cmdb.weichedao.com";
        String testUrl = cmdbUrl+"/itManage/rest/eth?type=dev";
        String stableUrl = cmdbUrl+"/itManage/rest/eth?type=stable";
        String onlineUrl = cmdbUrl+"/itManage/rest/eth?type=PRODUCTION";

        HttpClientResult testResult = HttpClientUtil.get(testUrl);
        HttpClientResult stableResult = HttpClientUtil.get(stableUrl);
        HttpClientResult onlineResult = HttpClientUtil.get(onlineUrl);

        String testJson = testResult.getData();
        JsonNode jsonNode = JsonUtil.getJsonNode(testJson).get("data");

        for(int i=0;i<jsonNode.size();i++){
            JsonNode firstNode = jsonNode.get(i);
            String hostname = firstNode.get("hostname").toString().replace("\"", "");

            String inHost = firstNode.get("eth0").toString().replace("\"", "");
            String outHost = firstNode.get("eth1").toString().replace("\"", "");
            System.out.println(hostname);
        }

    }

}
