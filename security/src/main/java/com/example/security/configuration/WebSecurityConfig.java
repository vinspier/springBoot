package com.example.security.configuration;

import com.example.security.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // 拓展security需配置继承该类

    @Bean
    UserDetailsService userService(){ // 注册userService该Bean
        return new UserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userService());// 添加自己定义的用户user details service认证
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated() //所有请求需要登录后才能访问
                .and()
                .formLogin()
                    .loginPage("/login")
                   // .failureUrl("login?error")
                    .permitAll() // 定制登录行为，登录页面可任意访问
                .and()
                .logout().permitAll(); // 定制注销行为，登录页面可任意访问
    }
}
