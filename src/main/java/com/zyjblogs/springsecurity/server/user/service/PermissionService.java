package com.zyjblogs.springsecurity.server.user.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author zhuyijun
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * zhuyijun         2020/12/31 9:57               1.0
 * @program springsecuritydemo
 * @description
 * @create 2020/12/31 9:57
 */
public interface PermissionService {
    /**
     *
     * @param request
     * @return
     */
    boolean hashPermission(HttpServletRequest request, Authentication authentication);
}
