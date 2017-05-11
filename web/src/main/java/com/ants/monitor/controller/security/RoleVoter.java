package com.ants.monitor.controller.security;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ants.monitor.bean.MonitorConstants;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * URL访问权限控制
 *
 * @author lixiao
 *         <p/>
 *         2014年12月30日 from legent-manager
 * update by zxg 2015/12/23
 */
public class RoleVoter implements AccessDecisionVoter<FilterInvocation> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation fi,
                    @SuppressWarnings("rawtypes") Collection attributes) {
        HttpSession session = fi.getRequest().getSession();
        String userName = (String) session.getAttribute(MonitorConstants.SESSION_USER_NAME);

        //用户存在在session中
        if (!StringUtils.isBlank(userName)) {
            //可做自定义权限控制，ACCESS_GRANTED为通过，展示
            return ACCESS_GRANTED;
        }
        //ACCESS_DENIED:不通过，不展示
        return ACCESS_DENIED;
    }

}
