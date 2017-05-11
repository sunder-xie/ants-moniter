package com.ants.monitor.controller.security;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.dao.redisManager.UserRedisManager;
import com.ants.monitor.common.tools.Tool;
import org.mitre.openid.connect.model.DefaultUserInfo;
import org.mitre.openid.connect.model.OIDCAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 登录认证filter
 * Created by yi.yang on 2014/7/30. from legent-manager
 * update by zxg 2015/12/23
 */
public class AuthenticationFilter extends org.mitre.openid.connect.client.OIDCAuthenticationFilter {

    @Autowired
    private UserRedisManager userRedisManager;

   @Value("${idp.url}")
   private String idpUrl;

    @Override
    protected Authentication handleAuthorizationCodeResponse(HttpServletRequest request,
                                                             HttpServletResponse response) {

        Authentication authentication = null;
        try {
            authentication = super.handleAuthorizationCodeResponse(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (authentication == null) {
            try {
                response.sendRedirect(request.getContextPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        if (!(authentication instanceof OIDCAuthenticationToken)) {
            return authentication;
        }

        DefaultUserInfo userInfo =
                (DefaultUserInfo) ((OIDCAuthenticationToken) authentication).getUserInfo();

        if (userInfo == null) {
            return authentication;
        }

        String staffNo = userInfo.getStaffNo();

        if(!StringUtils.isBlank(staffNo)) {
            //此处做自身的权限控制
            HttpSession session = request.getSession();
            String userName = userInfo.getUserName();
            session.setAttribute(MonitorConstants.SESSION_USER_NAME,userName);
            //保存ip和名称
//            String ip = Tool.getIpAddress(request);
//            userRedisManager.saveUserIpName(ip,userName);


        }
        return authentication;

    }
}
