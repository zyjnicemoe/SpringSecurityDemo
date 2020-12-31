package com.zyjblogs.springsecurity.server.user.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author zhuyijun
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * zhuyijun         2020/12/30 21:13               1.0
 * @program springsecuritydemo
 * @description
 * @create 2020/12/30 21:13
 */
@Controller
public class LoginController {
    /**
     * login登录
     * @return
     */
//    @PostMapping("/login")
//    public String login() {
//        return "redirect:main.html";
//    }
    //需要abc角色
    @Secured("ROLE_abc")
    //方法执行之前判断权限  PreAuthorize允许角色以ROLE_开头，也可以以ROLE_开头
//    @PreAuthorize("hasRole('abc')")
    @PostMapping("/toMain")
    public String main() {
//        return "redirect:main.html";
        return "main";
    }

    @PostMapping("/toError")
    public String error() {
        return "redirect:error.html";
    }

    @Secured("ROLE_abc")
    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

    @GetMapping("/showLogin")
    public String showLogin() {
        return "login";
    }

}
