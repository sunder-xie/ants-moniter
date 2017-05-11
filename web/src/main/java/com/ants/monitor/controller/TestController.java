package com.ants.monitor.controller;

import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.bean.ResultVO;
import com.ants.monitor.biz.dubboService.RegistryContainer;
import com.ants.monitor.common.tools.TimeUtil;
import com.ants.monitor.common.tools.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by zxg on 15/11/2.
 */

@Controller
@RequestMapping("/rest/test")
@Slf4j
public class TestController {
    @Autowired
    private RegistryContainer registryContainer;

    //测试
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO test(){
        System.out.println(""+new Date().toString());

        String host = "112.124.66.38";
        System.out.println(MonitorConstants.ecsMap.keySet().contains(host));
        String name = MonitorConstants.ecsMap.get(host);
        System.out.println(name+""+new Date().toString());
        return ResultVO.wrapSuccessfulResult(MonitorConstants.ecsMap.keySet());
    }

    //测试
    @RequestMapping(value = "/registryContainer", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO registryContainer(){
        registryContainer.start();
        return ResultVO.wrapSuccessfulResult("success");
    }


    //页面--正常样式
    @RequestMapping(value = "index" )
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("test/error");

        modelAndView.addObject("msg", "test");
        return modelAndView;
    }


    //测试
    @RequestMapping(value = "/getIp", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO getIp(HttpServletRequest request,String name){

        System.out.println(""+new Date().toString());
        String ip = Tool.getIpAddress(request);
        System.out.println(""+new Date().toString());
        return ResultVO.wrapSuccessfulResult(ip+name);
    }

}
