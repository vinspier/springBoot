package com.example.demo.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSocketSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("fxb").password("123").roles("USER") //  创建两个账号
                .and()
                .withUser("ty").password("123").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/resources/static/**"); // 对该路径下的文件 不进行拦截
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/login","/webSocket").permitAll() //  对/和/login路径不进行拦截
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") //  设置安全登录页面路径为/login
                .defaultSuccessUrl("/chat") // 登录成功后转向/chat路径
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
