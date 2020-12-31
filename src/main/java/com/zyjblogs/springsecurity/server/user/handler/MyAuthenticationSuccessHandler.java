package com.zyjblogs.springsecurity.server.user.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
 * zhuyijun         2020/12/30 22:41               1.0
 * @program springsecuritydemo
 * @description
 * @create 2020/12/30 22:41
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String url;
    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        System.out.println(request.getRemoteAddr());

        User user = (User) authentication.getPrincipal();
        System.out.println(user.getUsername());
        //输出null
        System.out.println(user.getPassword());
        System.out.println(user.getAuthorities());
        response.sendRedirect(url);
    }
}
