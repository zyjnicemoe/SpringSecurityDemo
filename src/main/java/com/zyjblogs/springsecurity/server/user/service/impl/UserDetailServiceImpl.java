package com.zyjblogs.springsecurity.server.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author zhuyijun
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * zhuyijun         2020/12/30 22:01               1.0
 * @program springsecuritydemo
 * @description
 * @create 2020/12/30 22:01
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("执行自定义登录逻辑");
        //1.根据用户名去数据库查询，如果不存在抛出UsernameNotFoundException异常
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //2.比较密码(注册时已经加密过)，如果匹配成功返回UserDetails
        String password = passwordEncoder.encode("123");

        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal,ROLE_abc,/main.html,/insert,/delete"));
    }
}
