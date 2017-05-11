import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.biz.support.consumer.SmsServiceHelp;
import com.ants.monitor.common.redis.RedisClientTemplate;
import com.ants.monitor.common.tools.JsonUtil;
import org.junit.runner.RunWith;


import com.tqmall.core.common.entity.Result;
import com.tqmall.tqmallstall.domain.result.CityListDTO;
import com.tqmall.tqmallstall.domain.result.sms.SmsDTO;
import com.tqmall.tqmallstall.service.common.AppRegionService;
import com.tqmall.tqmallstall.service.tools.AppToolsRemoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by zxg on 16/1/8.
 * 15:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/conf/application-context.xml"})
public class ServiceTest {
    @Autowired
    private SmsServiceHelp smsServiceHelp;
    @Autowired
    private RedisClientTemplate redisClientTemplate;

    @Test
    public void testSms(){
        System.out.println("\n===================================\n");
        // {$shop}{$day}单日发送会员短信超过{$limit}条！
        /*!!警告：服务于{$time}停止服务,它已停止{$stopTime}分钟了，(应用名：{$appName}，host地址：{$host}， 服务器：{$server})
        请及时排错重启，以免影响线上服务的使用。*/
        String mobile = "18868831869";
        Map<String, Object> map = new HashMap<>();
        map.put("time", "2016-03-21 12:30:08");
        map.put("stopTime", "3");
        map.put("appName", "athena");
        map.put("host", "127.0.0.1:2932");
        map.put("server", "just fun");

        Result<SmsDTO> result = smsServiceHelp.sendMsg(mobile, MonitorConstants.SMS_STOP_ACTION, map);
        System.out.println(JsonUtil.objectToJsonStr(result));

        System.out.println("\n===================================\n");
    }

    @Test
    public void testRedis() throws IOException, InterruptedException {
        String value = redisClientTemplate.get("a");
        System.out.println("Make SocketTimeoutException"+value);
        System.in.read(); //等待制造SocketTimeoutException
        try {
            value = redisClientTemplate.get("a");
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Recover from SocketTimeoutException");
        System.in.read();  //等待恢复
        Thread.sleep(5000); // 继续休眠一段时间 等待网络完全恢复
        boolean isMember = redisClientTemplate.exists("a");
        System.out.println(isMember);
    }
}
