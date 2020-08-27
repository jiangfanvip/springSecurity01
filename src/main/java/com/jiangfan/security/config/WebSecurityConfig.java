package com.jiangfan.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * spring security config
 *
 * @author LENOVO
 * @since JDK 1.8
 */
@EnableWebSecurity  //开启spring web security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {  //继承WebSecurityConfigurerAdapter
    //定义用户信息服务来查询用户信息--------------spring security帮我们定义好了UserDetailsService
    //我们需要让spring security验证账号密码，只需要申明就好，然后告诉它是通过数据库查还是本地查
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(); //基于内存模式
        UserDetails jiangfan= User.withUsername("jiangfan").password("123456").authorities("p1").build();//构建用户并设置权限(相当于数据库的用户)
        UserDetails lisi = User.withUsername("lisi").password("520123").authorities("p2").build();//构建用户并设置权限(相当于数据库的用户)
        inMemoryUserDetailsManager.createUser(jiangfan);  //创建构建好的用户
        inMemoryUserDetailsManager.createUser(lisi);//创建构建好的用户
        return inMemoryUserDetailsManager;
    }
    //密码编码器 来核对密码  passwordEncoder,纯字符串比对方式\
    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }
    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()   //授权请求的认证
                .antMatchers("/user/p1").hasAuthority("p1")  //访问/user/p1的用户需要p1权限
                .antMatchers("/user/p2").hasAuthority("p2")  //访问/user/p2的用户需要p1权限
                .antMatchers("/user/**").authenticated() //所有/user/**的请求资源必须认证通过
                .anyRequest().permitAll() //请求许可  其它请求资源可以放行
                .and().formLogin() //允许表单登录
                .successForwardUrl("/login-success");//登录成功跳转页面地址

    }
}
