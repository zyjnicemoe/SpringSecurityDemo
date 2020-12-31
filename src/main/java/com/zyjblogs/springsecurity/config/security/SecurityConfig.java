package com.zyjblogs.springsecurity.config.security;

import com.zyjblogs.springsecurity.server.user.handler.MyAccessDeniedHandler;
import com.zyjblogs.springsecurity.server.user.handler.MyAuthenticationFailureHandler;
import com.zyjblogs.springsecurity.server.user.handler.MyAuthenticationSuccessHandler;
import com.zyjblogs.springsecurity.server.user.handler.MyLogoutSuccessHandler;
import com.zyjblogs.springsecurity.server.user.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author zhuyijun
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * zhuyijun         2020/12/30 21:59               1.0
 * @program springsecuritydemo
 * @description
 * @create 2020/12/30 21:59
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //自动建表，第一次启动时开启，第二次启动时注释掉
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Bean
    public PasswordEncoder getPw() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单提交
        http.formLogin()
                //自定义用户名密码入参
                .usernameParameter("userName")
                .passwordParameter("passWord")
                //自定义登录页面
//                .loginPage("/login.html")
                .loginPage("/showLogin")
                //必须和表单提交的接口一样，获取执行自定义登录逻辑
                .loginProcessingUrl("/login")
                //登录成功后跳转的页面，必须是POST请求
                .successForwardUrl("/toMain")
                //自定义登录成功处理器
//                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
                //登录失败后转跳的页面，必须是POST请求
                .failureForwardUrl("/toError")
                //自定义登录失败处理器
//                .failureHandler(new MyAuthenticationFailureHandler("/error.html"))
                ;
        //拦截是有顺序的《从上往下》，.anyRequest().authenticated()需放最后
        http.authorizeRequests()
                //放行/error.html不需要认证
                .antMatchers("/error.html").permitAll()
                //放行/login.html不需要认证
                .antMatchers("/login.html").permitAll()
                .antMatchers("/showLogin").access("permitAll")
                //放行静态资源 符合ant表达式
                .antMatchers("/css/**","/js/**","/img/**").permitAll()
                //放行后缀png资源
//                .antMatchers("/**/*.png").permitAll()
                //正则匹配,放行后缀png资源,至少一个
//                .regexMatchers(".+[.]png").permitAll()
                //正则匹配 必须是POST
//                .regexMatchers(HttpMethod.POST,"/demo").permitAll()
//                .regexMatchers("/zyj/demo").permitAll()
                //mvc匹配
//                .mvcMatchers("/demo").servletPath("/zyj").permitAll()
                //权限控制，严格区分大小写  基于权限
//                .antMatchers("/main1.html").hasAuthority("admin")
//                .antMatchers("/main1.html").hasAnyAuthority("admin","adminN")
                //基于角色
                .antMatchers("/main1.html").hasRole("abc")
                //基于ip地址
//                .antMatchers("/main1.html").hasIpAddress("127.0.0.1")
                //所有请求都必须认证才能访问，必须登录
                .anyRequest().authenticated();
                //自定义access方法
//                .anyRequest().access("@permissionServiceImpl.hashPermission(request,authentication)")
       http.rememberMe()
               //设置数据源
               .tokenRepository(persistentTokenRepository)
//                .rememberMeParameter()
                .tokenValiditySeconds(60)
               //自定义登录逻辑
                .userDetailsService(userDetailService)
                ;

        //异常处理
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

        //退出
       http.logout()
//               .logoutUrl("/user/logout")
               .logoutUrl("/logout")
//               .logoutSuccessHandler(new MyLogoutSuccessHandler("/login.html"))
               .logoutSuccessUrl("/login.html");

        //关闭csrf防护
        //http.csrf().disable();
    }
}
