package com.zyjblogs.springsecurity.server.user.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author zhuyijun
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * zhuyijun         2020/12/31 14:34               1.0
 * @program springsecuritydemo
 * @description
 * @create 2020/12/31 14:34
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    String url;

    public MyLogoutSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.sendRedirect(url);
    }
}
