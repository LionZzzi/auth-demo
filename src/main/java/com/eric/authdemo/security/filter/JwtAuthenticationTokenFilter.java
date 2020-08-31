package com.eric.authdemo.security.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.eric.authdemo.constant.JwtConstants;
import com.eric.authdemo.security.UserDetailsServiceImpl;
import com.eric.authdemo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验用户传过来的token
 *
 * @author Eric
 * @link https://www.jianshu.com/p/f47c7b6b3bf7
 * @since 2020/8/29 10:20 上午
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("===== 进入JwtAuthenticationTokenFilter =====");
        String token = httpServletRequest.getHeader(JwtConstants.TOKEN);
        if (StrUtil.isNotEmpty(token)) {
            log.info("===== 开始校验token ====");
            String username = (String) jwtUtil.parseToken(token).get(JwtConstants.PAYLOAD_NAME);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            if (StrUtil.isNotEmpty(username) && ObjectUtil.isNull(securityContext.getAuthentication())) {
                log.info("===== 校验{} =====", username);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                log.info("===== 将用户{}放入Spring上下文中 =====", username);
                securityContext.setAuthentication(authentication);
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
        }
        log.info("===== 退出JwtAuthenticationTokenFilter =====");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
