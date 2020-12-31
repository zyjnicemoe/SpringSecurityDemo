package com.zyjblogs.springsecurity.server.user.service.impl;

import com.zyjblogs.springsecurity.server.user.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author zhuyijun
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * zhuyijun         2020/12/31 9:58               1.0
 * @program springsecuritydemo
 * @description
 * @create
 * 2020/12/31 9:58
 */
@Service
public class PermissionServiceImpl implements PermissionService {


    @Override
    public boolean hashPermission(HttpServletRequest request, Authentication authentication) {
        Object obj = authentication.getPrincipal();
        if (obj instanceof UserDetails) {
            Collection<? extends GrantedAuthority> authorities = ((UserDetails) obj).getAuthorities();
            return authorities.contains(new SimpleGrantedAuthority(request.getRequestURI()));
        }
        return false;
    }
}
