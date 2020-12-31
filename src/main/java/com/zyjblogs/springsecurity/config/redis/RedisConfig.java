package com.zyjblogs.springsecurity.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author zhuyijun
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * zhuyijun         2020/12/31 9:42               1.0
 * @program springsecuritydemo
 * @description
 * @create 2020/12/31 9:42
 */
public class RedisConfig {
    @Bean
    public RedisTemplate redisTemplate() {
            return new RedisTemplate();
    }

}
