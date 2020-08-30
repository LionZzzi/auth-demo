package com.eric.authdemo.config.security;

import com.eric.authdemo.security.UserDetailsServiceImpl;
import com.eric.authdemo.security.filter.JwtAuthenticationTokenFilter;
import com.eric.authdemo.security.handler.AuthenticationFailHandler;
import com.eric.authdemo.security.handler.AuthenticationSuccessHandler;
import com.eric.authdemo.security.handler.UnAccessDeniedHandler;
import com.eric.authdemo.security.handler.UnAuthenticatedEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author Eric
 * @since 2020/8/26 2:51 下午
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UnAuthenticatedEntryPoint unAuthenticatedEntryPoint;
    @Resource
    private UnAccessDeniedHandler unAccessDeniedHandler;
    @Resource
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private AuthenticationSuccessHandler successHandler;
    @Resource
    private AuthenticationFailHandler failHandler;
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用 Spring Security 自带的跨域处理
                .csrf().disable()
                // 授权异常处理
                .exceptionHandling()
                .authenticationEntryPoint(unAuthenticatedEntryPoint)
                .accessDeniedHandler(unAccessDeniedHandler)
                // 禁用Session
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 表单登录
                .formLogin().loginProcessingUrl("/auth/login")
                // 登陆成功处理
                .successHandler(successHandler)
                // 登陆失败处理
                .failureHandler(failHandler)
                .and()
                .authorizeRequests()
                // 允许用户访问登陆接口
//                .antMatchers("/login").permitAll()
//                .antMatchers(HttpMethod.POST, "/user/save").permitAll()
                // 放行OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 剩下所有的验证都需要验证
                .anyRequest().authenticated();
        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        http.headers().cacheControl();
    }
}
