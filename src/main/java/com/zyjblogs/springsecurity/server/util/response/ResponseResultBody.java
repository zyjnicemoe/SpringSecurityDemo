package com.zyjblogs.springsecurity.server.util.response;

import java.lang.annotation.*;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author JiangXincan
 * @version 1.0
 * <author>                <time>                  <version>                   <description>
 * Jiangxincan         2020/10/9 10:45             1.0                         返回值处理注解
 * @program xincan-framework
 * @description 返回值处理注解：统一包装返回值处理
 * @create 2020/10/9 10:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResultBody {
}
