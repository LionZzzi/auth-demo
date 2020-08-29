package com.eric.authdemo.security.handler;

import cn.hutool.core.map.MapUtil;
import com.eric.authdemo.model.SecurityUserDetails;
import com.eric.authdemo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("===== 登陆成功AuthenticationSuccessHandler =====");
        log.info("===== 生成TOKEN并返回 =====");
        Map<String, Object> map = MapUtil.newHashMap();
        SecurityUserDetails principal = (SecurityUserDetails) authentication.getPrincipal();
        map.put("id", principal.getId());
        map.put("name", principal.getName());
        String token = jwtUtil.create(map);
        log.info(token);
//        super.onAuthenticationSuccess(request, response, authentication);
    }
}
