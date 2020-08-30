package com.eric.authdemo.security.handler;

import com.eric.authdemo.model.common.Result;
import com.eric.authdemo.util.JwtUtil;
import com.eric.authdemo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eric
 * @since 2020/8/26 4:01 下午
 */
@Slf4j
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("===== 进入AuthenticationSuccessHandler =====");
        log.info("===== 登陆成功 =====");
        String token = jwtUtil.createByAuthentication(authentication);
        ResponseUtil.out(response, Result.success(token, "登陆成功"));
        log.info("===== 生成TOKEN并返回:{} =====", token);
    }
}
