package com.ants.monitor.controller.security;

import com.ants.monitor.bean.MonitorConstants;
import org.mitre.openid.connect.model.OIDCAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出后需要远程退出openid
 * Created by yi.yang on 2014/7/31. from legent-manager
 * update by zxg 2015/12/23
 */
/*此处处理为Controller，为了前端点击 登出的按钮，调用此方法，remove session中内容*/
@RestController
@RequestMapping("/monitor/log")
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        if (authentication instanceof OIDCAuthenticationToken == false) {
            response.sendRedirect(request.getContextPath());
            return;
        }
        request.getSession().removeAttribute(MonitorConstants.SESSION_USER_NAME);
        response.sendRedirect("/monitor/dash/main");


    }
}
