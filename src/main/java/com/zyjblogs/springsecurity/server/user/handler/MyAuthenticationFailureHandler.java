package com.zyjblogs.springsecurity.server.user.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

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
 * zhuyijun         2020/12/30 22:57               1.0
 * @program springsecuritydemo
 * @description
 * @create 2020/12/30 22:57
 */

/**
 * 登录失败处理器
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private String url;

    public MyAuthenticationFailureHandler(String url) {
        this.url = url;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.sendRedirect(url);
    }
}
